package com.nhanbt.demo_kotlin_native.features.weather.data.models

import com.squareup.moshi.Json

//@JsonClass(generateAdapter = true)
data class WeatherModel(
    @Json(name = "hourly")
    val weatherData: WeatherDataModel
)
