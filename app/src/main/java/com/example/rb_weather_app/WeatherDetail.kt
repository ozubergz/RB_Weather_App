package com.example.rb_weather_app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.rb_weather_app.R
import com.example.rb_weather_app.databinding.FragmentWeatherDetailBinding


class WeatherDetail : Fragment() {

    private lateinit var binding: FragmentWeatherDetailBinding
    private val args by navArgs<WeatherDetailArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentWeatherDetailBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val daily = args.data
        val location = args.location
        val weatherMain = daily.weather?.get(0)?.main ?: ""
        val weatherDesc = daily.weather?.get(0)?.description ?: ""
        val temp = daily.temp?.max.toString().substringBefore(".")
        val pressure = daily?.pressure
        val humidity = daily?.humidity
        val wind = daily?.windSpeed

        binding.tvLocation.text = location
        binding.tvWeatherMain.text = weatherMain
        binding.tvTemp.text = "$temp°F"
        binding.tvPressure.text = "pressure: $pressure"
        binding.tvHumidity.text = "humidity: $humidity"
        binding.tvWind.text = "wind: $wind"
        binding.tvWeatherDesc.text = weatherDesc
    }

}