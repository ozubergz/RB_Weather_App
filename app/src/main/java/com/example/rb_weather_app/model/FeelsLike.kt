package com.example.rb_weather_app.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeelsLike(
    val day: Double?,
    val night: Double?,
    val eve: Double?,
    val morn: Double?
)