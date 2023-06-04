package com.betulnecanli.weatherapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.betulnecanli.weatherapp.data.model.CurrentWeather
import com.betulnecanli.weatherapp.data.model.Daily
import com.betulnecanli.weatherapp.data.model.WeatherResponse
import com.betulnecanli.weatherapp.util.DatabaseConverter

@Database(
    entities = [WeatherResponse::class, CurrentWeather::class, Daily::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(DatabaseConverter::class)
abstract class WeatherDB: RoomDatabase() {

    abstract fun weatherDao() : WeatherDao
}