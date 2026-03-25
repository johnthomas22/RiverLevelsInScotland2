package com.riverlevels.scotland.ui.stations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.riverlevels.scotland.data.db.MonitoredStation
import com.riverlevels.scotland.databinding.ItemStationBinding

class StationAdapter(
    private val onItemClick: (MonitoredStation) -> Unit,
    private val onToggle: (MonitoredStation) -> Unit,
    private val onDelete: (MonitoredStation) -> Unit
) : ListAdapter<MonitoredStation, StationAdapter.ViewHolder>(DIFF) {

    inner class ViewHolder(private val b: ItemStationBinding) : RecyclerView.ViewHolder(b.root) {
        fun bind(s: MonitoredStation) {
            b.tvStationName.text = s.stationName
            b.tvRiverName.text = s.riverName
            b.tvCurrentLevel.text = s.currentLevel?.let { "%.3f m".format(it) } ?: "No data"
            b.tvStatus.text = when {
                s.currentLevel == null -> "Unknown"
                s.floodLevel != null && s.currentLevel >= s.floodLevel -> "FLOOD"
                s.alertLevel != null && s.currentLevel >= s.alertLevel -> "ALERT"
                else -> "Normal"
            }
            val statusColor = when (b.tvStatus.text) {
                "FLOOD" -> 0xFFD32F2F.toInt()
                "ALERT" -> 0xFFF57F17.toInt()
                else -> 0xFF388E3C.toInt()
            }
            b.tvStatus.setTextColor(statusColor)
            b.switchEnabled.isChecked = s.isEnabled
            b.switchEnabled.setOnCheckedChangeListener(null)
            b.switchEnabled.setOnCheckedChangeListener { _, _ -> onToggle(s) }
            b.btnDelete.setOnClickListener { onDelete(s) }
            b.root.setOnClickListener { onItemClick(s) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemStationBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<MonitoredStation>() {
            override fun areItemsTheSame(a: MonitoredStation, b: MonitoredStation) = a.stationNo == b.stationNo
            override fun areContentsTheSame(a: MonitoredStation, b: MonitoredStation) = a == b
        }
    }
}
