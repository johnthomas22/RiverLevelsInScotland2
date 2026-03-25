package com.riverlevels.scotland.data.api

import retrofit2.http.GET
import retrofit2.http.Query

// SEPA KiWIS API returns 2D arrays: first row = headers, rest = data rows
typealias SepaTable = List<List<String?>>

interface SepaApiService {

    @GET(".")
    suspend fun getStationList(
        @Query("service") service: String = "kisters",
        @Query("type") type: String = "queryServices",
        @Query("datasource") datasource: Int = 0,
        @Query("request") request: String = "getStationList",
        @Query("stationparameter_name") stationparameterName: String = "Level",
        @Query("returnfields") returnfields: String = "station_name,station_id,station_no,river_name",
        @Query("format") format: String = "json"
    ): SepaTable

    // Returns last 2 hours of 15-minute level readings for a station
    @GET(".")
    suspend fun getTimeseriesValues(
        @Query("service") service: String = "kisters",
        @Query("type") type: String = "queryServices",
        @Query("datasource") datasource: Int = 0,
        @Query("request") request: String = "getTimeseriesValues",
        @Query("ts_path") tsPath: String,
        @Query("period") period: String = "PT2H",
        @Query("returnfields") returnfields: String = "Timestamp,Value",
        @Query("format") format: String = "json"
    ): SepaTable
}
