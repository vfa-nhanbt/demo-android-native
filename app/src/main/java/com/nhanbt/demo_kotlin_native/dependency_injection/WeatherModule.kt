package com.nhanbt.demo_kotlin_native.dependency_injection

import com.nhanbt.demo_kotlin_native.features.weather.data.datasources.WeatherApi
import com.nhanbt.demo_kotlin_native.features.weather.data.repositories.WeatherRepositoryImp
import com.nhanbt.demo_kotlin_native.features.weather.domain.repositories.WeatherRepository
import com.nhanbt.demo_kotlin_native.features.weather.domain.usecases.GetWeather
import com.nhanbt.demo_kotlin_native.features.weather.domain.usecases.WeatherUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object WeatherModule {

    @Provides
    fun weatherRepository(
        api: WeatherApi
    ): WeatherRepository = WeatherRepositoryImp(api)

    @Provides
    fun weatherUseCases(
        repo: WeatherRepository
    ) = WeatherUseCases(
        getWeather = GetWeather(repo)
    )
}
