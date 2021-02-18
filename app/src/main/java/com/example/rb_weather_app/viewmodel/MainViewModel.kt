package com.example.rb_weather_app.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.rb_weather_app.BuildConfig
import com.example.rb_weather_app.R
import com.example.rb_weather_app.model.Weather
import com.example.rb_weather_app.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        private const val TAG = "ViewModel"
    }

    private val _weather = MutableLiveData<Weather>()

    val weather: LiveData<Weather>
        get() = _weather

//    val readAllData: LiveData<Weather>
//    private val repository: WeatherRepository
//
//    init {
//        val weatherDao = WeatherDatabase.getDataBase(application)?.weatherDao()
//        repository = weatherDao?.let { WeatherRepository(it) }!!
//        readAllData = repository.readAllData
//    }

    fun fetchAPIWeather(
        lat: Double,
        lon: Double,
        exclude: String = "hourly,minutely,alerts",
        units: String = "imperial",
        appid: String = R.string.weather_api_key.toString()
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val deferred = async {
                    val res = Repository.getWeather(lat, lon, exclude,units, appid)
                    _weather.postValue(res.body())
                }
                deferred.await()
            } catch (e: Exception) {
                Log.d(TAG, "fetchAPIWeather: $e")
            }
        }
    }
}