package com.riverlevels.scotland.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.riverlevels.scotland.data.api.SepaApiClient
import com.riverlevels.scotland.data.db.AppDatabase
import com.riverlevels.scotland.data.db.MonitoredStation
import com.riverlevels.scotland.data.model.StationInfo

class StationRepository(private val db: AppDatabase) {

    val monitoredStations: LiveData<List<MonitoredStation>> = db.stationDao().getAllStations()

    // Fetch all SEPA level stations (with coordinates) — used by map and search
    // Response columns: station_name, station_no, river_name, station_latitude, station_longitude
    suspend fun getAllSepaStations(): List<StationInfo> {
        return try {
            val table = SepaApiClient.service.getStationList()
            table.drop(1)
                .filter { row -> row.size >= 5 && !row[0].isNullOrBlank() && !row[1].isNullOrBlank() }
                .map { row ->
                    StationInfo(
                        stationName = row[0] ?: "",
                        stationNo = row[1] ?: "",
                        riverName = row[2] ?: "",
                        latitude = row[3]?.toDoubleOrNull(),
                        longitude = row[4]?.toDoubleOrNull()
                    )
                }
                .sortedBy { it.stationName }
        } catch (e: Exception) {
            Log.e("StationRepository", "getStationList failed: ${e.javaClass.simpleName}: ${e.message}", e)
            emptyList()
        }
    }

    // Filter cached/fetched list by query string (station name or river name)
    fun filterStations(stations: List<StationInfo>, query: String): List<StationInfo> {
        if (query.isBlank()) return stations
        val q = query.trim()
        return stations.filter { s ->
            s.stationName.contains(q, ignoreCase = true) ||
            s.riverName.contains(q, ignoreCase = true)
        }
    }

    // Fetch latest level reading for a station (returns null if unavailable)
    suspend fun fetchCurrentLevel(stationNo: String): Double? {
        return try {
            val tsPath = "1/$stationNo/SG/15m.Cmd"
            val table = SepaApiClient.service.getTimeseriesValues(tsPath = tsPath)
            table.drop(1)
                .lastOrNull { row -> row.size >= 2 && !row[1].isNullOrBlank() }
                ?.getOrNull(1)
                ?.toDoubleOrNull()
        } catch (e: Exception) {
            Log.e("StationRepository", "getTimeseriesValues failed for $stationNo: ${e.message}", e)
            null
        }
    }

    suspend fun addStation(station: MonitoredStation) = db.stationDao().insert(station)
    suspend fun updateStation(station: MonitoredStation) = db.stationDao().update(station)
    suspend fun deleteStation(station: MonitoredStation) = db.stationDao().delete(station)
    suspend fun getStation(stationNo: String) = db.stationDao().getStation(stationNo)
    suspend fun getEnabledStations() = db.stationDao().getEnabledStations()

    suspend fun updateLevel(stationNo: String, level: Double?, checkedAt: Long) =
        db.stationDao().updateLevel(stationNo, level, checkedAt)

    suspend fun updateAlertNotified(stationNo: String, time: Long) =
        db.stationDao().updateAlertNotified(stationNo, time)

    suspend fun updateFloodNotified(stationNo: String, time: Long) =
        db.stationDao().updateFloodNotified(stationNo, time)
}
