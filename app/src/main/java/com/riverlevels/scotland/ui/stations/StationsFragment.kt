package com.riverlevels.scotland.ui.stations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.riverlevels.scotland.R
import com.riverlevels.scotland.databinding.FragmentStationsBinding

class StationsFragment : Fragment() {

    private var _binding: FragmentStationsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: StationsViewModel by viewModels()

    private val adapter = StationAdapter(
        onItemClick = { station ->
            findNavController().navigate(
                R.id.action_stations_to_detail,
                bundleOf("stationNo" to station.stationNo)
            )
        },
        onToggle = { station -> viewModel.toggleEnabled(station) },
        onDelete = { station ->
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Remove station?")
                .setMessage("Stop monitoring ${station.stationName}?")
                .setPositiveButton("Remove") { _, _ -> viewModel.delete(station) }
                .setNegativeButton("Cancel", null)
                .show()
        }
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentStationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter

        viewModel.stations.observe(viewLifecycleOwner) { stations ->
            adapter.submitList(stations)
            binding.emptyView.visibility = if (stations.isEmpty()) View.VISIBLE else View.GONE
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_stations_to_addStation)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
