package com.nhanbt.demo_kotlin_native.features.weather.data.repositories

import com.nhanbt.demo_kotlin_native.core.response.BaseResponse
import com.nhanbt.demo_kotlin_native.features.weather.data.datasources.WeatherApi
import com.nhanbt.demo_kotlin_native.features.weather.data.helpers.toWeatherInfo
import com.nhanbt.demo_kotlin_native.features.weather.domain.entities.WeatherInfo
import com.nhanbt.demo_kotlin_native.features.weather.domain.repositories.WeatherRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImp @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {
    override suspend fun getWeatherData(lat: Double, long: Double): BaseResponse<WeatherInfo> {
        return try {
            BaseResponse.Success(
                data = api.getWeatherData(
                    lat, long
                ).toWeatherInfo()
            )
        } catch (e: Exception) {
            BaseResponse.Failure(e)
        }
    }

}
