package com.riverlevels.scotland.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.riverlevels.scotland.databinding.FragmentStationDetailBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class StationDetailFragment : Fragment() {

    private var _binding: FragmentStationDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: StationDetailViewModel by viewModels()

    private val stationNo: String by lazy {
        arguments?.getString("stationNo") ?: ""
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentStationDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        viewModel.load(stationNo)

        viewModel.station.observe(viewLifecycleOwner) { s ->
            if (s == null) return@observe
            binding.toolbar.title = s.stationName
            binding.tvRiver.text = "River: ${s.riverName}"
            binding.tvCurrentLevel.text = s.currentLevel?.let { "%.3f m".format(it) } ?: "No data"
            binding.tvLastChecked.text = if (s.lastChecked > 0) {
                "Last checked: ${SimpleDateFormat("dd MMM yyyy HH:mm", Locale.UK).format(Date(s.lastChecked))}"
            } else "Not yet checked"
            binding.etAlertLevel.setText(s.alertLevel?.toString() ?: "")
            binding.etFloodLevel.setText(s.floodLevel?.toString() ?: "")

            val statusText = when {
                s.currentLevel == null -> "Unknown"
                s.floodLevel != null && s.currentLevel >= s.floodLevel -> "FLOOD WARNING"
                s.alertLevel != null && s.currentLevel >= s.alertLevel -> "ALERT"
                else -> "Normal"
            }
            binding.tvStatus.text = statusText
            binding.tvStatus.setTextColor(when (statusText) {
                "FLOOD WARNING" -> 0xFFD32F2F.toInt()
                "ALERT" -> 0xFFF57F17.toInt()
                else -> 0xFF388E3C.toInt()
            })
        }

        viewModel.isRefreshing.observe(viewLifecycleOwner) { refreshing ->
            binding.swipeRefresh.isRefreshing = refreshing
        }

        binding.swipeRefresh.setOnRefreshListener { viewModel.refresh(stationNo) }
        binding.btnRefresh.setOnClickListener { viewModel.refresh(stationNo) }

        binding.btnSaveThresholds.setOnClickListener {
            val alert = binding.etAlertLevel.text.toString().toDoubleOrNull()
            val flood = binding.etFloodLevel.text.toString().toDoubleOrNull()
            if (flood != null && alert != null && flood <= alert) {
                Snackbar.make(binding.root, "Flood level must be higher than alert level", Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }
            viewModel.saveThresholds(stationNo, alert, flood)
            Snackbar.make(binding.root, "Thresholds saved", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
