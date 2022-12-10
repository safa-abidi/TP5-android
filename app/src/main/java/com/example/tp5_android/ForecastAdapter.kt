package com.example.tp5_android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp5_android.models.DayInfo
import com.example.tp5_android.models.Forecast
import java.util.*

class ForecastAdapter(private val data: MutableList<DayInfo>) :
    RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    var data1 : MutableList<DayInfo> = data

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val temp: TextView
        val hum: TextView

        init {
            temp = itemView.findViewById(R.id.dayTemp)
            hum = itemView.findViewById(R.id.dayHum)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.forecast_day, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.temp.text = "Temperature ${data[position].temp.day.toString()}"
        holder.hum.text = "Humidity ${data[position].humidity.toString()}"
    }
    override fun getItemCount(): Int {
        return this.data1.size
    }




}