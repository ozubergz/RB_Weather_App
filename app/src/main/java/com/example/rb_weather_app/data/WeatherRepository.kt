package com.example.rb_weather_app.data

import androidx.lifecycle.LiveData
import com.example.rb_weather_app.model.Weather

class WeatherRepository(private val weatherDao: WeatherDao) {



    suspend fun addWeather(weather: Weather) {
        weatherDao.addWeather(weather)
    }
}