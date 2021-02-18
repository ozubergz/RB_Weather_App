package com.example.rb_weather_app.repo

import com.example.rb_weather_app.model.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    // onecall?lat=40.712776&lon=-74.005974&exclude=hourly,minutely,alerts&units=imperial&appid=109194221acd94dd5ff8d1c28ead9754

    @GET("onecall")
    suspend fun getWeather(
            @Query("lat") lat: Double,
            @Query("lon") lon: Double,
            @Query("exclude") exclude: String,
            @Query("units") units: String,
            @Query("appid") appid: String
    ) : Response<Weather>

}