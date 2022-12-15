package com.nhanbt.demo_kotlin_native.features.weather.data.helpers

import com.nhanbt.demo_kotlin_native.features.weather.data.models.WeatherDataModel
import com.nhanbt.demo_kotlin_native.features.weather.data.models.WeatherModel
import com.nhanbt.demo_kotlin_native.features.weather.domain.entities.WeatherInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherInfo(
    val index: Int,
    val data: WeatherInfo
)

fun WeatherDataModel.toWeatherInfoMap(): Map<Int, List<WeatherInfo>> {
    return time.mapIndexed { index, time ->
        IndexedWeatherInfo(
            index = index,
            data = WeatherInfo(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperatures[index],
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { it.data }
    }
}

fun WeatherModel.toWeatherInfo(): WeatherInfo {
    val weatherInfoMap = weatherData.toWeatherInfoMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherInfoMap[0]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }

    return currentWeatherData !!
}
