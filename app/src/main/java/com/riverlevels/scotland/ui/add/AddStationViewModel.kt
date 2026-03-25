package com.riverlevels.scotland.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.riverlevels.scotland.data.db.AppDatabase
import com.riverlevels.scotland.data.db.MonitoredStation
import com.riverlevels.scotland.data.model.StationInfo
import com.riverlevels.scotland.data.repository.StationRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AddStationViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = StationRepository(AppDatabase.getInstance(app))

    val searchResults = MutableLiveData<List<StationInfo>>(emptyList())
    val isLoading = MutableLiveData(false)
    val error = MutableLiveData<String?>(null)

    private var searchJob: Job? = null

    fun search(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(300) // debounce
            isLoading.value = true
            error.value = null
            val results = repo.searchSepaStations(query)
            if (results.isEmpty() && query.isNotBlank()) {
                error.value = "No stations found matching \"$query\""
            }
            searchResults.value = results
            isLoading.value = false
        }
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
