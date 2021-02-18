package com.example.rb_weather_app.adapter

import com.example.rb_weather_app.model.Daily

interface ClickListener {
    fun itemClick(item: Daily)
}