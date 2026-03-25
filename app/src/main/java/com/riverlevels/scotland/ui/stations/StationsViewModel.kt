package com.riverlevels.scotland.ui.stations

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.riverlevels.scotland.data.db.AppDatabase
import com.riverlevels.scotland.data.db.MonitoredStation
import com.riverlevels.scotland.data.repository.StationRepository
import kotlinx.coroutines.launch

class StationsViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = StationRepository(AppDatabase.getInstance(app))
    val stations = repo.monitoredStations

    fun delete(station: MonitoredStation) = viewModelScope.launch {
        repo.deleteStation(station)
    }

    fun toggleEnabled(station: MonitoredStation) = viewModelScope.launch {
        repo.updateStation(station.copy(isEnabled = !station.isEnabled))
    }
}
