package com.example.rb_weather_app.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rb_weather_app.model.Weather

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addWeather(weather: Weather)

    @Query("SELECT * FROM weather_table limit 1")
    fun readData() : Weather
}