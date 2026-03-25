package com.riverlevels.scotland.ui.map

import android.view.View
import com.riverlevels.scotland.R
import com.riverlevels.scotland.data.db.MonitoredStation
import com.riverlevels.scotland.data.model.StationInfo
import com.riverlevels.scotland.databinding.MarkerInfoWindowBinding
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.infowindow.InfoWindow

class StationInfoWindow(
    mapView: MapView,
    private val station: StationInfo,
    private val monitored: MonitoredStation?,
    private val onAdd: () -> Unit,
    private val onView: () -> Unit
) : InfoWindow(R.layout.marker_info_window, mapView) {

    override fun onOpen(item: Any?) {
        val b = MarkerInfoWindowBinding.bind(mView)
        b.tvStationName.text = station.stationName
        b.tvRiverName.text = station.riverName.ifBlank { "Unknown river" }

        if (monitored != null) {
            b.tvLevel.text = monitored.currentLevel?.let { "%.3f m".format(it) } ?: "Monitoring — no data yet"
            b.btnAdd.visibility = View.GONE
            b.btnView.visibility = View.VISIBLE
        } else {
            b.tvLevel.text = "Not monitored"
            b.btnAdd.visibility = View.VISIBLE
            b.btnView.visibility = View.GONE
        }

        b.btnAdd.setOnClickListener { onAdd() }
        b.btnView.setOnClickListener { onView() }
        b.btnClose.setOnClickListener { close() }
    }

    override fun onClose() {}
}
