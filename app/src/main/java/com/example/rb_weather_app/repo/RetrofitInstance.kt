package com.example.rb_weather_app.repo


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {

    // https://api.openweathermap.org/data/2.5/

    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    private val client = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }.let {
            OkHttpClient.Builder().addInterceptor(it).build()
        }


//    private fun getClient(): OkHttpClient {
//        val client = HttpLoggingInterceptor()
//        client.level = HttpLoggingInterceptor.Level.BODY
//        return OkHttpClient.Builder().addInterceptor(client).build()
//    }

    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()

    val apiService: APIService by lazy {
        retrofit.create(APIService::class.java)
    }
}