package com.example.rb_weather_app.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.rb_weather_app.databinding.DailyItemBinding
import com.example.rb_weather_app.databinding.FragmentMainBinding
import com.example.rb_weather_app.model.Daily
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class WeatherAdapter(private val dataSet: List<Daily>, private val listener: ClickListener) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    class ViewHolder(private val binding: DailyItemBinding, private val listener: ClickListener):RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat", "SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: Daily) {
            val weather = item.weather?.get(0)?.main ?: ""
            val temp = item.temp?.max.toString().substringBefore(".")

            binding.tvWeatherMain.text = weather
            binding.tvTemp.text = "$temp°F"

            binding.root.setOnClickListener {
                listener.itemClick(item)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = DailyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v, listener)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}