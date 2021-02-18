package com.example.rb_weather_app.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherX(
    val id: Int?,
    val main: String?,
    val description: String?,
    val icon: String?
)