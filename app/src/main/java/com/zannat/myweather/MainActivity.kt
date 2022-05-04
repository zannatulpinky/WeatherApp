package com.zannat.myweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        weatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]

        val tvCityName = findViewById<TextView>(R.id.tvCityName)
        val tvWeatherDescription = findViewById<TextView>(R.id.tvWeatherDescription)
        val tvTemp = findViewById<TextView>(R.id.tvTemp)
        val tvHumidity = findViewById<TextView>(R.id.tvHumidity)
        val tvWindSpeed = findViewById<TextView>(R.id.tvWindSpeed)
        val tvGeoCoordinate = findViewById<TextView>(R.id.tvGeoCoordinate)
        val etSearch = findViewById<EditText>(R.id.etSearch)
        val btnSearch = findViewById<Button>(R.id.btnSearch)


        weatherViewModel.weatherResponse.observe(this) {
            tvCityName.text = it.name
            tvWeatherDescription.text = "Weather Description: ${it.weather[0].description}"
            tvTemp.text = "Temp ${it.main?.temp}"
            tvHumidity.text = "Humidity ${it.main?.humidity}"
            tvWindSpeed.text = "Wind Speed: ${it.wind?.speed}"
            tvGeoCoordinate.text = "GeoCoordinate: ${it.coord?.lat} ${it.coord?.lon}"
        }

        btnSearch.setOnClickListener {
            val cityName = etSearch.text.toString().trim()
            if (cityName.isNotEmpty()) {
                weatherViewModel.getWeatherData(cityName)
            } else {
                etSearch.error = "Please enter city name"
            }
        }

        weatherViewModel.getWeatherData("Stockholm")
    }

}