package com.riverlevels.scotland.data.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StationDao {
    @Query("SELECT * FROM monitored_stations ORDER BY stationName ASC")
    fun getAllStations(): LiveData<List<MonitoredStation>>

    @Query("SELECT * FROM monitored_stations WHERE isEnabled = 1")
    suspend fun getEnabledStations(): List<MonitoredStation>

    @Query("SELECT * FROM monitored_stations WHERE stationNo = :stationNo")
    suspend fun getStation(stationNo: String): MonitoredStation?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(station: MonitoredStation)

    @Update
    suspend fun update(station: MonitoredStation)

    @Delete
    suspend fun delete(station: MonitoredStation)

    @Query("UPDATE monitored_stations SET currentLevel = :level, lastChecked = :checkedAt WHERE stationNo = :stationNo")
    suspend fun updateLevel(stationNo: String, level: Double?, checkedAt: Long)

    @Query("UPDATE monitored_stations SET lastAlertNotified = :time WHERE stationNo = :stationNo")
    suspend fun updateAlertNotified(stationNo: String, time: Long)

    @Query("UPDATE monitored_stations SET lastFloodNotified = :time WHERE stationNo = :stationNo")
    suspend fun updateFloodNotified(stationNo: String, time: Long)
}
