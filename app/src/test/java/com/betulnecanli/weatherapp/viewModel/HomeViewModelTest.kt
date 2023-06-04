package com.betulnecanli.weatherapp.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.betulnecanli.weatherapp.MainCoroutineRule
import com.betulnecanli.weatherapp.getOrAwaitValueTest
import com.betulnecanli.weatherapp.data.model.CurrentWeather
import com.betulnecanli.weatherapp.data.model.Daily
import com.betulnecanli.weatherapp.data.model.WeatherResponse
import com.betulnecanli.weatherapp.repository.FakeWeatherRepository
import com.betulnecanli.weatherapp.ui.home.HomeViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var fakeWeatherRepository: FakeWeatherRepository
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        fakeWeatherRepository = FakeWeatherRepository()
        viewModel = HomeViewModel(fakeWeatherRepository)
    }

    @Test
    fun `when getDataFromService is succesful, then update weatherData`() = runTest {
        val currentWeather = CurrentWeather(is_day = 1, temperature = 20.0)
        val daily = Daily(
            apparent_temperature_max = listOf(25.0),
            apparent_temperature_min = listOf(15.0),
            time = listOf("10:00")
        )
        val expectedResponse = WeatherResponse(current_weather = currentWeather , daily = daily)
        viewModel.getDataFromService(1.0, 1.0)
        val actualResponse = viewModel.weatherData.getOrAwaitValueTest()
        assertEquals(expectedResponse, actualResponse )
    }


    @Test
    fun `when getDataFromService fails, then weatherData is null`() = runTest {
        fakeWeatherRepository.setReturnError(true)
        viewModel.getDataFromService(10.0,90.5)
        val actualResponse = viewModel.weatherData.getOrAwaitValueTest()
        assertNull(actualResponse)
    }

}