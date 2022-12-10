package com.example.tp5_android.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import com.example.tp5_android.RetrofitHelper
import com.example.tp5_android.models.Forecast
import com.example.tp5_android.models.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel : ViewModel() {
    val weather: MutableLiveData<WeatherResponse> by lazy {
        MutableLiveData<WeatherResponse>()
    }
    val forecast : MutableLiveData<Forecast> by lazy {
        MutableLiveData<Forecast>()
    }
    init {
        getWeather("Madrid")
    }
    fun getWeather(city: String){
        RetrofitHelper.retrofitService.getWeather(city).enqueue(object : Callback<WeatherResponse>{
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if(response.isSuccessful){
                    weather.value = response.body()
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d("failed", t.message.toString())
            }
        })
    }

    //Forecast
    fun getForecast(city: String){
        RetrofitHelper.retrofitService.getForecast(city).enqueue(object : Callback<Forecast>{
            override fun onResponse(
                call: Call<Forecast>,
                response: Response<Forecast>
            ) {
                if(response.isSuccessful){
                    forecast.value = response.body()
                    Log.d("success", response.body().toString())
                }
            }

            override fun onFailure(call: Call<Forecast>, t: Throwable) {
                Log.d("failed", t.message.toString())
            }
        })
    }
}