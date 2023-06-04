package com.betulnecanli.weatherapp.repository

import com.betulnecanli.weatherapp.data.model.CurrentWeather
import com.betulnecanli.weatherapp.data.model.Daily
import com.betulnecanli.weatherapp.data.model.WeatherResponse
import com.betulnecanli.weatherapp.data.repository.WeatherRepositoryInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeWeatherRepository : WeatherRepositoryInterface {
    private var shouldReturnError = false

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override suspend fun getDataFromService(
        latitude: Double, longitude: Double
    ): Flow<WeatherResponse?> = flow {

        val currentWeather = CurrentWeather(is_day = 1, temperature = 20.0)
        val daily = Daily(
            apparent_temperature_max = listOf(25.0),
            apparent_temperature_min = listOf(15.0),
            time = listOf("10:00")
        )
        val weatherResponse = WeatherResponse(current_weather = currentWeather, daily = daily)

        if (shouldReturnError) {
            emit(null)
        } else {
            emit(weatherResponse)
        }
    }
}