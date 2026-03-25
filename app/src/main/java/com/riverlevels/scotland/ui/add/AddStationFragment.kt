package com.riverlevels.scotland.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.riverlevels.scotland.databinding.FragmentAddStationBinding

class AddStationFragment : Fragment() {

    private var _binding: FragmentAddStationBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddStationViewModel by viewModels()

    private val adapter = SearchStationAdapter { station ->
        viewModel.addStation(station)
        Snackbar.make(binding.root, "${station.stationName} added", Snackbar.LENGTH_SHORT).show()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAddStationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        binding.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        binding.searchInput.addTextChangedListener { text ->
            viewModel.search(text?.toString() ?: "")
        }

        viewModel.searchResults.observe(viewLifecycleOwner) { adapter.submitList(it) }
        viewModel.isLoading.observe(viewLifecycleOwner) { loading ->
            binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
        }
        viewModel.error.observe(viewLifecycleOwner) { msg ->
            binding.tvError.text = msg ?: ""
            binding.tvError.visibility = if (msg != null) View.VISIBLE else View.GONE
        }

        // Load all stations initially
        viewModel.search("")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
