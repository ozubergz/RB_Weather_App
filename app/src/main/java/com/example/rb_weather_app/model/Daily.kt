package com.example.rb_weather_app.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.RawValue
import kotlinx.parcelize.Parcelize


@Parcelize
@JsonClass(generateAdapter = true)
data class Daily(
        val dt: Int?,
        val sunrise: Int?,
        val sunset: Int?,
        val temp: @RawValue Temp?,
        @Json(name = "feels_like")
        val feelsLike: @RawValue FeelsLike?,
        val pressure: Int?,
        val humidity: Int?,
        @Json(name = "dew_point")
        val dewPoint: Double?,
        @Json(name = "wind_speed")
        val windSpeed: Double?,
        @Json(name = "wind_deg")
        val windDeg: Int?,
        val weather: @RawValue List<WeatherXX>?,
        val clouds: Int?,
        val pop: Double?,
        val uvi: Double?,
        val snow: Double?,
        val rain: Double?
) : Parcelable