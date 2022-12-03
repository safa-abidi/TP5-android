package com.example.tp5_android

import androidx.activity.viewModels
import com.example.tp5_android.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val model : WeatherViewModel by viewModels()

    val spinner : Spinner by lazy {findViewById(R.id.country)}

    private lateinit var country : TextView
    private lateinit var desc : TextView
    private lateinit var temp : TextView
    private lateinit var humidity : TextView
    private lateinit var pressure : TextView
    var countries = arrayOf<String>("Tunis", "London", "Madrid")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        //country = binding.country
        desc = binding.desc
        temp = binding.temperature
        humidity = binding.humidity
        pressure = binding.pressure

        setContentView(binding.root)

        spinner.adapter = ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,countries)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val country = countries.get(position)
                Toast.makeText(this@MainActivity, "$country", Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
            }
        }
        model.weather.observe(this, Observer {
            if(it != null){
                //country.text = it.name
                temp.text = it.main.temp.toString()
                desc.text = it.weather[0].description
                humidity.text = it.main.humidity.toString()
                pressure.text = it.main.pressure.toString()

            }
        })

    }
}