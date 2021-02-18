package com.example.rb_weather_app.repo

import com.example.rb_weather_app.model.Weather
import retrofit2.Response

object Repository {

    private val apiService = RetrofitInstance.apiService

    suspend fun getWeather(
        lat: Double,
        lon: Double,
        exclude: String,
        units: String,
        appid: String
    ) : Response<Weather> {
        return apiService.getWeather(lat, lon, exclude, units, appid)
    }
}