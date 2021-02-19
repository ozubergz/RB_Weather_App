package com.example.rb_weather_app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rb_weather_app.adapter.ClickListener
import com.example.rb_weather_app.adapter.WeatherAdapter
import com.example.rb_weather_app.databinding.FragmentMainBinding
import com.example.rb_weather_app.model.Daily
import com.example.rb_weather_app.viewmodel.MainViewModel


class MainFragment : Fragment(), ClickListener {

    private lateinit var binding: FragmentMainBinding
    private lateinit var location: String
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentMainBinding.inflate(inflater, container, false)
        .also { binding = it }.root


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        activity.let {
//            viewModel = ViewModelProvider(it!!).get(MainViewModel::class.java)
//        }

        viewModel.weather.observe(viewLifecycleOwner, Observer {
            val current = it.current
            val temp = current?.temp.toString().substringBefore(".")
            val weather = current?.weather?.get(0)?.main
            val pressure = current?.pressure
            val humidity = current?.humidity
            val wind = current?.windSpeed

            this.location = it.timezone.toString()

            binding.tvLocation.text = it.timezone
            binding.tvTemp.text = "$temp°F"
            binding.tvWeatherMain.text = weather
            binding.tvPressure.text = "pressure: $pressure"
            binding.tvHumidity.text = "humidity: $humidity"
            binding.tvWind.text = "wind: $wind"

            binding.rvDailyList.adapter = it.daily?.let { daily ->
                WeatherAdapter(daily, this)
            }

            binding.rvDailyList.layoutManager = LinearLayoutManager(context)
        })
    }

    override fun itemClick(item: Daily) {
        val action = MainFragmentDirections.actionMainFragmentToWeatherDetail(item, location)
        findNavController().navigate(action)
    }
}