package com.riverlevels.scotland.util

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class AppPreferences(context: Context) {

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val prefs = EncryptedSharedPreferences.create(
        context,
        "river_levels_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    var smtpHost: String
        get() = prefs.getString(KEY_SMTP_HOST, "") ?: ""
        set(v) = prefs.edit().putString(KEY_SMTP_HOST, v).apply()

    var smtpPort: Int
        get() = prefs.getInt(KEY_SMTP_PORT, 587)
        set(v) = prefs.edit().putInt(KEY_SMTP_PORT, v).apply()

    var smtpUsername: String
        get() = prefs.getString(KEY_SMTP_USER, "") ?: ""
        set(v) = prefs.edit().putString(KEY_SMTP_USER, v).apply()

    var smtpPassword: String
        get() = prefs.getString(KEY_SMTP_PASS, "") ?: ""
        set(v) = prefs.edit().putString(KEY_SMTP_PASS, v).apply()

    var alertEmailTo: String
        get() = prefs.getString(KEY_EMAIL_TO, "") ?: ""
        set(v) = prefs.edit().putString(KEY_EMAIL_TO, v).apply()

    // Polling interval in minutes (minimum 15 due to WorkManager constraints)
    var pollingIntervalMinutes: Int
        get() = prefs.getInt(KEY_POLL_INTERVAL, 30)
        set(v) = prefs.edit().putInt(KEY_POLL_INTERVAL, v).apply()

    // Cooldown before re-notifying the same station (millis)
    var notificationCooldownMillis: Long
        get() = prefs.getLong(KEY_NOTIF_COOLDOWN, 60 * 60 * 1000L) // 1 hour default
        set(v) = prefs.edit().putLong(KEY_NOTIF_COOLDOWN, v).apply()

    var smtpUseSsl: Boolean
        get() = prefs.getBoolean(KEY_SMTP_SSL, false)
        set(v) = prefs.edit().putBoolean(KEY_SMTP_SSL, v).apply()

    companion object {
        private const val KEY_SMTP_HOST = "smtp_host"
        private const val KEY_SMTP_PORT = "smtp_port"
        private const val KEY_SMTP_USER = "smtp_user"
        private const val KEY_SMTP_PASS = "smtp_pass"
        private const val KEY_EMAIL_TO = "email_to"
        private const val KEY_POLL_INTERVAL = "poll_interval"
        private const val KEY_NOTIF_COOLDOWN = "notif_cooldown"
        private const val KEY_SMTP_SSL = "smtp_ssl"

        @Volatile private var INSTANCE: AppPreferences? = null
        fun getInstance(context: Context): AppPreferences =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: AppPreferences(context.applicationContext).also { INSTANCE = it }
            }
    }
}
