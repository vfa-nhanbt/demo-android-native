package com.nhanbt.demo_kotlin_native.features.weather.domain.usecases

import com.nhanbt.demo_kotlin_native.features.weather.domain.repositories.WeatherRepository
import javax.inject.Inject

class GetWeather @Inject constructor(
    private val repo: WeatherRepository
) {
    suspend operator fun invoke(lat: Double, long: Double) = repo.getWeatherData(lat, long)
}
