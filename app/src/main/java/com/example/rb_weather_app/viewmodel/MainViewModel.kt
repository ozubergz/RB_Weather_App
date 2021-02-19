package com.example.rb_weather_app.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.rb_weather_app.R
import com.example.rb_weather_app.model.Weather
import com.example.rb_weather_app.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.concurrent.TimeUnit

class MainViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        private const val TIME_STAMP_KEY = "TIME_STAMP_KEY"
    }

//    private val sharedPref = application.getSharedPreferences("", Context.MODE_PRIVATE)

    private val _weather = MutableLiveData<Weather>()

    val weather: LiveData<Weather>
        get() = _weather

//    init {
//        val weatherDao = WeatherDatabase.getDataBase(application)?.weatherDao()
//        repository = weatherDao?.let { WeatherRepository(it) }!!
//
//    }

    fun fetchAPIWeather(
        lat: Double,
        lon: Double,
        exclude: String = "hourly,minutely,alerts",
        units: String = "imperial",
        appid: String = R.string.weather_api_key.toString()
    ) {

        viewModelScope.launch(Dispatchers.IO) {
            val res = Repository.getWeather(getApplication(), lat, lon, exclude,units, appid)
            _weather.postValue(res!!)

//            while (true) {
//                launch {
//                    val res = Repository.getWeather(getApplication(), lat, lon, exclude,units, appid)
//                    _weather.postValue(res!!)
//                }
//                delay(600_000)  // 10 minute delay (suspending)
//            }

//            try {
//                val deferred = async {
//                    val res = Repository.getWeather(getApplication(), lat, lon, exclude,units, appid)
//                    _weather.postValue(res!!)
//                }
//                deferred.await()
//            } catch (e: Exception) {
//                Log.d(TAG, "fetchAPIWeather: $e")
//            }
        }
    }
}