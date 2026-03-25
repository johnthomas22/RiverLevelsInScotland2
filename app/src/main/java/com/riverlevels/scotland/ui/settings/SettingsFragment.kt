package com.riverlevels.scotland.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.riverlevels.scotland.databinding.FragmentSettingsBinding
import com.riverlevels.scotland.util.AppPreferences
import com.riverlevels.scotland.worker.LevelCheckWorker

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val prefs = AppPreferences.getInstance(requireContext())

        // Populate existing values
        binding.etSmtpHost.setText(prefs.smtpHost)
        binding.etSmtpPort.setText(prefs.smtpPort.toString())
        binding.etSmtpUsername.setText(prefs.smtpUsername)
        binding.etSmtpPassword.setText(prefs.smtpPassword)
        binding.etEmailTo.setText(prefs.alertEmailTo)
        binding.etPollInterval.setText(prefs.pollingIntervalMinutes.toString())
        binding.switchSsl.isChecked = prefs.smtpUseSsl

        binding.btnSave.setOnClickListener {
            prefs.smtpHost = binding.etSmtpHost.text.toString().trim()
            prefs.smtpPort = binding.etSmtpPort.text.toString().toIntOrNull() ?: 587
            prefs.smtpUsername = binding.etSmtpUsername.text.toString().trim()
            prefs.smtpPassword = binding.etSmtpPassword.text.toString()
            prefs.alertEmailTo = binding.etEmailTo.text.toString().trim()
            prefs.smtpUseSsl = binding.switchSsl.isChecked
            val interval = binding.etPollInterval.text.toString().toIntOrNull()?.coerceAtLeast(15) ?: 30
            prefs.pollingIntervalMinutes = interval

            // Re-schedule worker with new interval
            LevelCheckWorker.schedule(requireContext(), interval)
            Snackbar.make(binding.root, "Settings saved", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
