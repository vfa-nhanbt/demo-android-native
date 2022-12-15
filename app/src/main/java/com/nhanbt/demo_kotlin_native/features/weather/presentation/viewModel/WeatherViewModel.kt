package com.nhanbt.demo_kotlin_native.features.weather.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhanbt.demo_kotlin_native.core.response.BaseResponse
import com.nhanbt.demo_kotlin_native.features.weather.domain.entities.WeatherInfo
import com.nhanbt.demo_kotlin_native.features.weather.domain.usecases.WeatherUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val useCases: WeatherUseCases,
) : ViewModel() {
    var weatherResponse by mutableStateOf<BaseResponse<WeatherInfo>>(BaseResponse.Loading)
        private set

    init {
        getWeatherData()
    }

    private fun getWeatherData() = viewModelScope.launch {
        useCases.getWeather(10.875, 106.625).also {
            weatherResponse = it
        }
    }
}
