package com.betulnecanli.weatherapp.repository

import com.betulnecanli.weatherapp.data.local.WeatherDao
import com.betulnecanli.weatherapp.data.model.CurrentWeather
import com.betulnecanli.weatherapp.data.model.Daily
import com.betulnecanli.weatherapp.data.model.WeatherResponse
import com.betulnecanli.weatherapp.data.network.WeatherService
import com.betulnecanli.weatherapp.data.repository.WeatherRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class WeatherRepositoryTest {

    @Mock
    lateinit var weatherService: WeatherService

    @Mock
    lateinit var weatherDao: WeatherDao

    private lateinit var repository: WeatherRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = WeatherRepository(weatherService, weatherDao)
    }

    @Test
    fun test_null() = runTest{
        Mockito.`when`(weatherService.getWeatherResult(90.0, 90.0)).thenReturn(null)

        repository.getDataFromService(90.0, 90.0).collect{
            assertNull(it)
        }
    }

    @Test
    fun test_value() = runTest{
        Mockito.`when`(weatherService.getWeatherResult(90.0, 90.0)).thenReturn(
            WeatherResponse(
                1,
                CurrentWeather(1, 10, 20.0),
                Daily(1, listOf(1.0,2.0), listOf(1.1,2.2), listOf("m", "t"))
            )
        )
        var result: WeatherResponse? = null
        repository.getDataFromService(90.0, 90.0).collect{
            result = it
        }
        assertTrue(result is WeatherResponse)
    }

}