package com.riverlevels.scotland.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.riverlevels.scotland.data.db.AppDatabase
import com.riverlevels.scotland.data.db.MonitoredStation
import com.riverlevels.scotland.data.repository.StationRepository
import kotlinx.coroutines.launch

class StationDetailViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = StationRepository(AppDatabase.getInstance(app))

    val station = MutableLiveData<MonitoredStation?>()
    val isRefreshing = MutableLiveData(false)

    fun load(stationNo: String) = viewModelScope.launch {
        station.value = repo.getStation(stationNo)
    }

    fun refresh(stationNo: String) = viewModelScope.launch {
        isRefreshing.value = true
        val level = repo.fetchCurrentLevel(stationNo)
        val now = System.currentTimeMillis()
        repo.updateLevel(stationNo, level, now)
        station.value = repo.getStation(stationNo)
        isRefreshing.value = false
    }

    fun saveThresholds(stationNo: String, alertLevel: Double?, floodLevel: Double?) = viewModelScope.launch {
        val current = repo.getStation(stationNo) ?: return@launch
        repo.updateStation(current.copy(alertLevel = alertLevel, floodLevel = floodLevel))
        station.value = repo.getStation(stationNo)
    }
}
