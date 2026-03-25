package com.riverlevels.scotland.ui.map

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.riverlevels.scotland.R
import com.riverlevels.scotland.data.db.MonitoredStation
import com.riverlevels.scotland.data.model.StationInfo
import com.riverlevels.scotland.databinding.FragmentMapBinding
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.infowindow.MarkerInfoWindow

class MapFragment : Fragment() {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MapViewModel by viewModels()
    private lateinit var map: MapView

    // Scotland center
    private val SCOTLAND_CENTER = GeoPoint(57.1, -4.2)
    private val SCOTLAND_ZOOM = 7.5

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Configuration.getInstance().userAgentValue = requireContext().packageName
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        map = binding.map
        map.setTileSource(TileSourceFactory.MAPNIK)
        map.setMultiTouchControls(true)
        map.controller.setZoom(SCOTLAND_ZOOM)
        map.controller.setCenter(SCOTLAND_CENTER)

        viewModel.isLoading.observe(viewLifecycleOwner) { loading ->
            binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(viewLifecycleOwner) { msg ->
            if (msg != null) Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG).show()
        }

        // Redraw markers whenever stations or monitored set changes
        viewModel.stations.observe(viewLifecycleOwner) { stations ->
            updateMarkers(stations, viewModel.monitoredMap.value ?: emptyMap())
        }
        viewModel.monitoredMap.observe(viewLifecycleOwner) { monitored ->
            val stations = viewModel.stations.value ?: return@observe
            updateMarkers(stations, monitored)
        }
    }

    private fun updateMarkers(stations: List<StationInfo>, monitored: Map<String, MonitoredStation>) {
        map.overlays.clear()

        for (station in stations) {
            val lat = station.latitude ?: continue
            val lon = station.longitude ?: continue

            val marker = Marker(map)
            marker.position = GeoPoint(lat, lon)
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            marker.title = station.stationName
            marker.snippet = station.riverName

            val monStation = monitored[station.stationNo]
            marker.icon = requireContext().getDrawable(
                when {
                    monStation == null -> R.drawable.ic_marker_blue
                    monStation.currentLevel != null && monStation.floodLevel != null &&
                        monStation.currentLevel >= monStation.floodLevel -> R.drawable.ic_marker_red
                    monStation.currentLevel != null && monStation.alertLevel != null &&
                        monStation.currentLevel >= monStation.alertLevel -> R.drawable.ic_marker_yellow
                    else -> R.drawable.ic_marker_green
                }
            )

            marker.setOnMarkerClickListener { m, _ ->
                m.showInfoWindow()
                map.controller.animateTo(m.position)
                true
            }

            marker.infoWindow = StationInfoWindow(map, station, monStation,
                onAdd = {
                    viewModel.addStation(station)
                    Snackbar.make(binding.root, "${station.stationName} added to monitoring", Snackbar.LENGTH_SHORT).show()
                    marker.closeInfoWindow()
                },
                onView = {
                    marker.closeInfoWindow()
                    findNavController().navigate(
                        R.id.action_map_to_detail,
                        bundleOf("stationNo" to station.stationNo)
                    )
                }
            )

            map.overlays.add(marker)
        }

        map.invalidate()
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
