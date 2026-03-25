package com.riverlevels.scotland.email

import android.util.Log
import com.riverlevels.scotland.util.AppPreferences
import java.util.Properties
import javax.mail.Message
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

object EmailHelper {
    private const val TAG = "EmailHelper"

    fun sendAlert(
        prefs: AppPreferences,
        subject: String,
        body: String
    ) {
        val host = prefs.smtpHost
        val port = prefs.smtpPort
        val username = prefs.smtpUsername
        val password = prefs.smtpPassword
        val to = prefs.alertEmailTo

        if (host.isBlank() || username.isBlank() || password.isBlank() || to.isBlank()) {
            Log.w(TAG, "Email not configured — skipping alert email")
            return
        }

        try {
            val props = Properties().apply {
                put("mail.smtp.auth", "true")
                put("mail.smtp.host", host)
                put("mail.smtp.port", port.toString())
                if (prefs.smtpUseSsl) {
                    put("mail.smtp.ssl.enable", "true")
                    put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
                } else {
                    put("mail.smtp.starttls.enable", "true")
                }
            }

            val authenticator = object : javax.mail.Authenticator() {
                override fun getPasswordAuthentication() =
                    javax.mail.PasswordAuthentication(username, password)
            }

            val authenticatedSession = Session.getInstance(props, authenticator)

            val msg = MimeMessage(authenticatedSession).apply {
                setFrom(InternetAddress(username))
                setRecipients(Message.RecipientType.TO, InternetAddress.parse(to))
                setSubject(subject)
                setText(body)
            }

            Transport.send(msg)
            Log.i(TAG, "Alert email sent to $to")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to send alert email: ${e.message}", e)
        }
    }

    fun buildAlertBody(
        stationName: String,
        riverName: String,
        level: Double,
        alertLevel: Double?,
        floodLevel: Double?,
        type: String
    ): String = buildString {
        appendLine("River Level $type Alert")
        appendLine("=".repeat(40))
        appendLine("Station:     $stationName")
        appendLine("River:       $riverName")
        appendLine("Level:       ${String.format("%.3f", level)} m")
        alertLevel?.let { appendLine("Alert level: ${String.format("%.3f", it)} m") }
        floodLevel?.let { appendLine("Flood level: ${String.format("%.3f", it)} m") }
        appendLine()
        appendLine("This is an automated alert from River Levels in Scotland.")
    }
}
