package com.nhanbt.demo_kotlin_native.features.weather.data.datasources

import com.nhanbt.demo_kotlin_native.features.weather.data.models.WeatherModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("v1/forecast?hourly=temperature_2m")
    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ): WeatherModel
}
