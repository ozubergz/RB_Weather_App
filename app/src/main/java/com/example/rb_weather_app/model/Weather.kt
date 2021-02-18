package com.example.rb_weather_app.model


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Entity(tableName = "weather_table")
@Parcelize
@JsonClass(generateAdapter = true)
data class Weather @JvmOverloads constructor(
    val lat: Double,
    val lon: Double,
    val timezone: String,
    @Json(name = "timezone_offset")
    val timezoneOffset: Int,
    @Ignore val current: @RawValue Current? = null,
    @Ignore val daily: List<Daily>? = listOf()
) : Parcelable {

    @PrimaryKey(autoGenerate = false)
    var id: Long = 0
}