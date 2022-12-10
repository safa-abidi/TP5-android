package com.example.tp5_android

import android.content.Intent
import androidx.activity.viewModels
import com.example.tp5_android.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tp5_android.models.Weather
import com.example.tp5_android.viewModel.WeatherViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    //private val model : WeatherViewModel by viewModels()

    val spinner : Spinner by lazy {findViewById(R.id.spinner)}

    private lateinit var desc : TextView
    private lateinit var temp : TextView
    private lateinit var humidity : TextView
    private lateinit var pressure : TextView
    private lateinit var icon : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

//        val viewModel: WeatherViewModel by viewModels { WeatherViewModel.Factory }
       val viewModel : WeatherViewModel by viewModels()
        var countries = arrayOf("Madrid", "London", "Tunis", "Paris", "Barcelona")
        desc = binding.desc
        temp = binding.temperature
        humidity = binding.humidity
        pressure = binding.pressure
        icon = binding.icon

        setContentView(binding.root)

        spinner.adapter = ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,countries)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //val country = countries.get(position)
                val country = spinner.selectedItem.toString()
                Toast.makeText(this@MainActivity, country, Toast.LENGTH_LONG).show()
                viewModel.getWeather(country)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
            }
        }
        viewModel.weather.observe(this, Observer {
            if(it != null){
                temp.text = it.main.temp.toString()
                desc.text = it.weather[0].description
                humidity.text = it.main.humidity.toString()
                pressure.text = it.main.pressure.toString()
                DownloadImageFromInternet(binding.icon)
                    .execute("https://openweathermap.org/img/wn/${it.weather[0].icon}@2x.png")
            }
        })

    }

    fun goToMore(view: View) {
        val intent = Intent(view.context,DetailsActivity::class.java)
        intent.putExtra("country",spinner.selectedItem.toString())
        Log.d("country", spinner.selectedItem.toString())
        startActivity(intent)
    }
}