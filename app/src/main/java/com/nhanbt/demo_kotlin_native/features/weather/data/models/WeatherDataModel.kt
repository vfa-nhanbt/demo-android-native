package com.nhanbt.demo_kotlin_native.features.weather.data.models

import com.squareup.moshi.Json

//@JsonClass(generateAdapter = true)
data class WeatherDataModel(
//    @field:Json(name = "time")
    val time: List<String>,
    @Json(name = "temperature_2m")
    val temperatures: List<Double>,
)
