package com.riverlevels.scotland.data.model

import com.google.gson.annotations.SerializedName

data class TimeseriesResult(
    @SerializedName("ts_id") val tsId: String = "",
    @SerializedName("rows") val rows: String = "0",
    @SerializedName("columns") val columns: String = "",
    // Each row is [timestampString, numericValue] — mixed types so Any? is used
    @SerializedName("data") val data: List<List<Any?>> = emptyList()
)
