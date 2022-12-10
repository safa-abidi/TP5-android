package com.example.tp5_android

import com.example.tp5_android.models.Forecast
import com.example.tp5_android.models.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("weather?&APPID=17db59488cadcad345211c36304a9266")
    fun getWeather(@Query("q") name: String) : Call<WeatherResponse>

    @GET("forecast/daily?&APPID=17db59488cadcad345211c36304a9266")
    fun getForecast(@Query("q") name: String) : Call<Forecast>
}
