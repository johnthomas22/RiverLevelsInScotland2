package com.riverlevels.scotland

import android.app.Application
import com.riverlevels.scotland.notification.NotificationHelper
import com.riverlevels.scotland.util.AppPreferences
import com.riverlevels.scotland.worker.LevelCheckWorker

class RiverLevelsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        NotificationHelper.createChannels(this)
        val prefs = AppPreferences.getInstance(this)
        LevelCheckWorker.schedule(this, prefs.pollingIntervalMinutes)
    }
}
