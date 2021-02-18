package com.example.rb_weather_app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rb_weather_app.model.Weather

@Database(entities = [Weather::class], version = 1)
abstract class WeatherDatabase: RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {
        @Volatile
        private var INSTANCE: WeatherDatabase? = null

        fun getDataBase(context: Context): WeatherDatabase? {
            val tempInstance = INSTANCE
            if(tempInstance != INSTANCE) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java,
                    "weather_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}