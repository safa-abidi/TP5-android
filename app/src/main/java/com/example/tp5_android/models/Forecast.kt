package com.example.tp5_android.models

data class Forecast(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<DayInfo>,
    val message: Double
)