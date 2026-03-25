package com.riverlevels.scotland.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.riverlevels.scotland.MainActivity
import com.riverlevels.scotland.R

object NotificationHelper {

    private const val CHANNEL_ALERT = "river_alert"
    private const val CHANNEL_FLOOD = "river_flood"

    fun createChannels(context: Context) {
        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.createNotificationChannel(
            NotificationChannel(CHANNEL_ALERT, "River Alert", NotificationManager.IMPORTANCE_HIGH).apply {
                description = "Notifies when river reaches alert level"
            }
        )
        nm.createNotificationChannel(
            NotificationChannel(CHANNEL_FLOOD, "River Flood", NotificationManager.IMPORTANCE_HIGH).apply {
                description = "Notifies when river reaches flood level"
            }
        )
    }

    fun sendAlertNotification(context: Context, stationName: String, riverName: String, level: Double, threshold: Double) {
        send(
            context,
            channelId = CHANNEL_ALERT,
            notifId = stationName.hashCode(),
            title = "River Alert: $stationName",
            message = "$riverName is at ${String.format("%.2f", level)}m (alert threshold: ${String.format("%.2f", threshold)}m)"
        )
    }

    fun sendFloodNotification(context: Context, stationName: String, riverName: String, level: Double, threshold: Double) {
        send(
            context,
            channelId = CHANNEL_FLOOD,
            notifId = stationName.hashCode() + 1,
            title = "Flood Warning: $stationName",
            message = "$riverName is at ${String.format("%.2f", level)}m (flood threshold: ${String.format("%.2f", threshold)}m)"
        )
    }

    private fun send(context: Context, channelId: String, notifId: Int, title: String, message: String) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        val pi = PendingIntent.getActivity(context, notifId, intent, PendingIntent.FLAG_IMMUTABLE)

        val notif = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_water_level)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pi)
            .build()

        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.notify(notifId, notif)
    }
}
