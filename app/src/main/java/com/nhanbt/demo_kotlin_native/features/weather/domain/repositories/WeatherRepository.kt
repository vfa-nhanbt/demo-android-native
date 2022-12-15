package com.nhanbt.demo_kotlin_native.features.weather.domain.repositories

import com.nhanbt.demo_kotlin_native.core.response.BaseResponse
import com.nhanbt.demo_kotlin_native.features.weather.domain.entities.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): BaseResponse<WeatherInfo>
}
