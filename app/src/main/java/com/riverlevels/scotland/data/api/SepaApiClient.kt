package com.riverlevels.scotland.data.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object SepaApiClient {
    // The SEPA KiWIS servlet does NOT accept a trailing slash — keep base URL exactly as-is
    // and use @GET("KiWIS") in the service interface so Retrofit appends to the parent path.
    private const val BASE_URL = "https://timeseries.sepa.org.uk/KiWIS/"

    val service: SepaApiService by lazy {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(SepaApiService::class.java)
    }
}
