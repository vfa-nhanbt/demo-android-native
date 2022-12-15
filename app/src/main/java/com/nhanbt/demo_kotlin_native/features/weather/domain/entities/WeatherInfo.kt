package com.nhanbt.demo_kotlin_native.features.weather.domain.entities

import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class WeatherInfo(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
) {
    fun temperatureString(): String {
        return "${roundOffDecimal(temperatureCelsius)} °C"
    }

    fun timeString(): String {
        return DateTimeFormatter
            .ofPattern("hh:mm")
            .format(time.toLocalTime())
    }

    private fun roundOffDecimal(number: Double): Double? {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return df.format(number).toDouble()
    }
}
