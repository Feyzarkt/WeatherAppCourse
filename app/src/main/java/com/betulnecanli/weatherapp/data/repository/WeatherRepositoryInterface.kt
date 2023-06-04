package com.betulnecanli.weatherapp.data.repository

import com.betulnecanli.weatherapp.data.model.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepositoryInterface {

    suspend fun getDataFromService(latitude: Double , longitude : Double): Flow<WeatherResponse?>

}