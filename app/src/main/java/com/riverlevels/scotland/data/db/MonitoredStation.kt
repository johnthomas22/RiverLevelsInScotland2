package com.riverlevels.scotland.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "monitored_stations")
data class MonitoredStation(
    @PrimaryKey val stationNo: String,
    val stationName: String,
    val riverName: String,
    val alertLevel: Double?,       // user-set alert threshold (metres)
    val floodLevel: Double?,       // user-set flood threshold (metres)
    val currentLevel: Double?,     // most recently fetched level
    val lastChecked: Long = 0L,    // epoch millis
    val lastAlertNotified: Long = 0L,
    val lastFloodNotified: Long = 0L,
    val isEnabled: Boolean = true
)
