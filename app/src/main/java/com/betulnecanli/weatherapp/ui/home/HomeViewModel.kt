package com.betulnecanli.weatherapp.ui.home

import androidx.lifecycle.*
import com.betulnecanli.weatherapp.data.model.WeatherResponse
import com.betulnecanli.weatherapp.data.repository.WeatherRepository
import com.betulnecanli.weatherapp.data.repository.WeatherRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val weatherRepository: WeatherRepositoryInterface) :
    ViewModel() {

    val weatherData: MutableLiveData<WeatherResponse?> = MutableLiveData()

    fun getDataFromService(lat: Double, longitude : Double) = viewModelScope.launch {
        weatherRepository.getDataFromService(lat, longitude).collect { data ->
            weatherData.postValue(data)
        }
    }
}