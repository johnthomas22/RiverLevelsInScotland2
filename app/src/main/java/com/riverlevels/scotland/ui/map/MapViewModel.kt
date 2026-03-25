package com.riverlevels.scotland.ui.map

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.riverlevels.scotland.data.db.AppDatabase
import com.riverlevels.scotland.data.db.MonitoredStation
import com.riverlevels.scotland.data.model.StationInfo
import com.riverlevels.scotland.data.repository.StationRepository
import kotlinx.coroutines.launch

class MapViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = StationRepository(AppDatabase.getInstance(app))

    val stations = MutableLiveData<List<StationInfo>>(emptyList())
    val monitoredMap = MutableLiveData<Map<String, MonitoredStation>>(emptyMap())
    val isLoading = MutableLiveData(false)
    val error = MutableLiveData<String?>(null)

    init {
        load()
        // Observe monitored stations for colour updates
        repo.monitoredStations.observeForever { list ->
            monitoredMap.value = list.associateBy { it.stationNo }
        }
    }

    fun load() = viewModelScope.launch {
        isLoading.value = true
        error.value = null
        val result = repo.getAllSepaStations()
        if (result.isEmpty()) {
            error.value = "Could not load stations — check your connection"
        }
        stations.value = result
        isLoading.value = false
    }

    fun addStation(info: StationInfo) = viewModelScope.launch {
        repo.addStation(
            MonitoredStation(
                stationNo = info.stationNo,
                stationName = info.stationName,
                riverName = info.riverName,
                alertLevel = null,
                floodLevel = null,
                currentLevel = null
            )
        )
    }
}
