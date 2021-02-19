package com.example.rb_weather_app.repo

import android.content.Context
import android.util.Log
import com.example.rb_weather_app.data.WeatherDatabase
import com.example.rb_weather_app.model.Weather
import kotlinx.coroutines.launch
import retrofit2.Response

object Repository {

    private const val TIME_STAMP_KEY = "TIME_STAMP_KEY"
    private const val TAG = "Repo"

    suspend fun getWeather(
            context: Context,
            lat: Double,
            lon: Double,
            exclude: String,
            units: String,
            appid: String
        ) : Weather? {

            val sharedPref = context.getSharedPreferences("", Context.MODE_PRIVATE)
            var savedTime = sharedPref.getLong(TIME_STAMP_KEY, 0)
            if(savedTime > 0) {
                sharedPref.edit().putLong(TIME_STAMP_KEY, System.currentTimeMillis()).apply()
                savedTime = (savedTime / 1000) / 60
            }
            val currTime = (System.currentTimeMillis() / 1000) / 60
            val diffTime = currTime - savedTime

            Log.d(TAG, "getWeather: $diffTime")

            val weatherDao = WeatherDatabase.getDataBase(context)?.weatherDao()

            if(diffTime >= 10) {
                val response = RetrofitInstance.apiService.getWeather(lat, lon, exclude, units, appid)
                response.body()?.let { weatherDao?.addWeather(it) }
            }

            Log.d(TAG, "getWeather: ${weatherDao?.readData()}")

            return weatherDao?.readData()
        }
}