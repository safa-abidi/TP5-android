package com.example.tp5_android

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("weather?q={name}&APPID=17db59488cadcad345211c36304a9266")
    fun getWeather(@Query("name") name: String ) : Call<WeatherResponse>
}
