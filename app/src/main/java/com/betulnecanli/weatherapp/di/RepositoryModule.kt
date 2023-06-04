package com.betulnecanli.weatherapp.di

import com.betulnecanli.weatherapp.data.local.WeatherDao
import com.betulnecanli.weatherapp.data.network.WeatherService
import com.betulnecanli.weatherapp.data.repository.WeatherRepository
import com.betulnecanli.weatherapp.data.repository.WeatherRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(
        weatherService: WeatherService,
        weatherDao: WeatherDao
    ): WeatherRepositoryInterface = WeatherRepository(weatherService, weatherDao)
}