package com.riverlevels.scotland.worker

import android.content.Context
import android.util.Log
import androidx.work.*
import com.riverlevels.scotland.data.db.AppDatabase
import com.riverlevels.scotland.data.db.MonitoredStation
import com.riverlevels.scotland.data.repository.StationRepository
import com.riverlevels.scotland.email.EmailHelper
import com.riverlevels.scotland.notification.NotificationHelper
import com.riverlevels.scotland.util.AppPreferences
import java.util.concurrent.TimeUnit

class LevelCheckWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    private val repo = StationRepository(AppDatabase.getInstance(context))
    private val prefs = AppPreferences.getInstance(context)

    override suspend fun doWork(): Result {
        Log.d(TAG, "Starting level check")
        val stations = repo.getEnabledStations()

        stations.forEach { station ->
            checkStation(station)
        }

        return Result.success()
    }

    private suspend fun checkStation(station: MonitoredStation) {
        val level = repo.fetchCurrentLevel(station.stationNo)
        val now = System.currentTimeMillis()

        repo.updateLevel(station.stationNo, level, now)

        if (level == null) {
            Log.w(TAG, "No level data for ${station.stationName}")
            return
        }

        val cooldown = prefs.notificationCooldownMillis

        // Check flood level first (higher priority)
        val floodLevel = station.floodLevel
        if (floodLevel != null && level >= floodLevel) {
            if (now - station.lastFloodNotified > cooldown) {
                NotificationHelper.sendFloodNotification(
                    applicationContext, station.stationName, station.riverName, level, floodLevel
                )
                sendEmail(station, level, "Flood")
                repo.updateFloodNotified(station.stationNo, now)
                Log.i(TAG, "Flood alert sent for ${station.stationName}: $level >= $floodLevel")
            }
            return
        }

        // Check alert level
        val alertLevel = station.alertLevel
        if (alertLevel != null && level >= alertLevel) {
            if (now - station.lastAlertNotified > cooldown) {
                NotificationHelper.sendAlertNotification(
                    applicationContext, station.stationName, station.riverName, level, alertLevel
                )
                sendEmail(station, level, "Alert")
                repo.updateAlertNotified(station.stationNo, now)
                Log.i(TAG, "Alert sent for ${station.stationName}: $level >= $alertLevel")
            }
        }
    }

    private fun sendEmail(station: MonitoredStation, level: Double, type: String) {
        val subject = "River $type: ${station.stationName} (${station.riverName})"
        val body = EmailHelper.buildAlertBody(
            station.stationName, station.riverName, level,
            station.alertLevel, station.floodLevel, type
        )
        EmailHelper.sendAlert(prefs, subject, body)
    }

    companion object {
        private const val TAG = "LevelCheckWorker"
        const val WORK_NAME = "river_level_check"

        fun schedule(context: Context, intervalMinutes: Int = 30) {
            val request = PeriodicWorkRequestBuilder<LevelCheckWorker>(
                intervalMinutes.toLong(), TimeUnit.MINUTES
            )
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
                .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.UPDATE,
                request
            )
        }

        fun cancel(context: Context) {
            WorkManager.getInstance(context).cancelUniqueWork(WORK_NAME)
        }
    }
}
