package com.example.tp5_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp5_android.databinding.ActivityDetailsBinding
import com.example.tp5_android.models.DayInfo
import com.example.tp5_android.models.Forecast
import com.example.tp5_android.viewModel.WeatherViewModel
import java.util.*

class DetailsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var data : List<DayInfo>
        val model : WeatherViewModel by viewModels()
        super.onCreate(savedInstanceState)

        var binding : ActivityDetailsBinding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var city = binding.city
        var recyclerView = binding.recycler


        var c : String? = intent.getStringExtra("country");
        city.text = c
        if(c != null){
            model.getForecast(c)
        }

        model.forecast.observe(this) { it ->
            if (it != null) {
                data = it.list
                recyclerView.adapter = ForecastAdapter(data as MutableList<DayInfo>)
                recyclerView.layoutManager = LinearLayoutManager(this@DetailsActivity)
            }
        }



    }


}