package com.riverlevels.scotland.data.api

import com.riverlevels.scotland.data.model.TimeseriesResult
import retrofit2.http.GET
import retrofit2.http.Query

// Base URL is https://timeseries.sepa.org.uk/KiWIS/
// The servlet path is KiWIS — no trailing slash (server returns 404 with one)

// Station list: 2D array  [["header",...],["val",...],...]
typealias SepaTable = List<List<String?>>

interface SepaApiService {

    // Returns station_name, station_no, river_name, station_latitude, station_longitude
    @GET("KiWIS")
    suspend fun getStationList(
        @Query("service") service: String = "kisters",
        @Query("type") type: String = "queryServices",
        @Query("datasource") datasource: Int = 0,
        @Query("request") request: String = "getStationList",
        @Query("stationparameter_name") stationparameterName: String = "Level",
        @Query("returnfields") returnfields: String = "station_name,station_no,river_name,station_latitude,station_longitude",
        @Query("format") format: String = "json"
    ): SepaTable

    // Returns station custom_attributes including historical level statistics
    @GET("KiWIS")
    suspend fun getStationAttributes(
        @Query("service") service: String = "kisters",
        @Query("type") type: String = "queryServices",
        @Query("datasource") datasource: Int = 0,
        @Query("request") request: String = "getStationList",
        @Query("station_no") stationNo: String,
        @Query("returnfields") returnfields: String = "station_no,custom_attributes",
        @Query("format") format: String = "json"
    ): SepaTable

    // Returns [{"ts_id":...,"data":[[timestamp, value],...]}]
    @GET("KiWIS")
    suspend fun getTimeseriesValues(
        @Query("service") service: String = "kisters",
        @Query("type") type: String = "queryServices",
        @Query("datasource") datasource: Int = 0,
        @Query("request") request: String = "getTimeseriesValues",
        @Query("ts_path") tsPath: String,
        @Query("period") period: String = "PT2H",
        @Query("returnfields") returnfields: String = "Timestamp,Value",
        @Query("format") format: String = "json"
    ): List<TimeseriesResult>
}
