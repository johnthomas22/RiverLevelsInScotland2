package com.riverlevels.scotland.ui.add

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.riverlevels.scotland.data.model.StationInfo
import com.riverlevels.scotland.databinding.ItemSearchStationBinding

class SearchStationAdapter(
    private val onAdd: (StationInfo) -> Unit
) : ListAdapter<StationInfo, SearchStationAdapter.ViewHolder>(DIFF) {

    inner class ViewHolder(private val b: ItemSearchStationBinding) : RecyclerView.ViewHolder(b.root) {
        fun bind(s: StationInfo) {
            b.tvStationName.text = s.stationName
            b.tvRiverName.text = s.riverName.ifBlank { "—" }
            b.btnAdd.setOnClickListener { onAdd(s) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemSearchStationBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<StationInfo>() {
            override fun areItemsTheSame(a: StationInfo, b: StationInfo) = a.stationNo == b.stationNo
            override fun areContentsTheSame(a: StationInfo, b: StationInfo) = a == b
        }
    }
}
