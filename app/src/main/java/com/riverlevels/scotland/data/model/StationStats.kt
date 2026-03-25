package com.riverlevels.scotland.data.model

data class StationStats(
    val medianAnnualMax: Double?,   // sepa_median_annual_maximum_level
    val recordMax: Double?,         // sepa_maximum_level
    val recordMin: Double?          // sepa_minimum_level
)
