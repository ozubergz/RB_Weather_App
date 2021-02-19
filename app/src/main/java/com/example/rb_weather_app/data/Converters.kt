package com.example.rb_weather_app.data

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.rb_weather_app.model.Daily
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class Converters {

    @TypeConverter
    fun dailyToString(daily: List<Daily>) : String? {
        val type = Types.newParameterizedType(List::class.java, Daily::class.java)
        val adapter = Moshi.Builder().build().adapter<List<Daily>>(type)
        return adapter.toJson(daily)
    }

    @TypeConverter
    fun stringToDaily(json: String) : List<Daily>? {
        val type = Types.newParameterizedType(List::class.java, Daily::class.java)
        val adapter = Moshi.Builder().build().adapter<List<Daily>>(type)
        return adapter.fromJson(json)
    }

}