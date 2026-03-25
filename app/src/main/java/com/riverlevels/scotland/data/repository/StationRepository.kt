package com.riverlevels.scotland.data.repository

import androidx.lifecycle.LiveData
import com.riverlevels.scotland.data.api.SepaApiClient
import com.riverlevels.scotland.data.db.AppDatabase
import com.riverlevels.scotland.data.db.MonitoredStation
import com.riverlevels.scotland.data.model.StationInfo

class StationRepository(private val db: AppDatabase) {

    val monitoredStations: LiveData<List<MonitoredStation>> = db.stationDao().getAllStations()

    // Fetch all SEPA level stations for the search/add screen
    suspend fun searchSepaStations(query: String): List<StationInfo> {
        return try {
            val table = SepaApiClient.service.getStationList()
            // table[0] = headers: station_name, station_id, station_no, river_name
            table.drop(1)
                .filter { row ->
                    row.size >= 4 &&
                    (query.isBlank() ||
                     row[0]?.contains(query, ignoreCase = true) == true ||
                     row[3]?.contains(query, ignoreCase = true) == true)
                }
                .map { row ->
                    StationInfo(
                        stationName = row[0] ?: "",
                        stationId = row[1] ?: "",
                        stationNo = row[2] ?: "",
                        riverName = row[3] ?: ""
                    )
                }
                .sortedBy { it.stationName }
        } catch (e: Exception) {
            emptyList()
        }
    }

    // Fetch latest level reading for a station (returns null if unavailable)
    suspend fun fetchCurrentLevel(stationNo: String): Double? {
        return try {
            val tsPath = "1/$stationNo/SG/15m.Cmd"
            val table = SepaApiClient.service.getTimeseriesValues(tsPath = tsPath)
            // Find last non-null value (skip header row)
            table.drop(1)
                .lastOrNull { row -> row.size >= 2 && row[1] != null && row[1] != "" }
                ?.getOrNull(1)
                ?.toDoubleOrNull()
        } catch (e: Exception) {
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
