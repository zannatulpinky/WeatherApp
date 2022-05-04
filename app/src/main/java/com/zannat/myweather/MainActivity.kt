package com.zannat.myweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvCityName = findViewById<TextView>(R.id.tvCityName)
        val tvWeatherDescription = findViewById<TextView>(R.id.tvWeatherDescription)
        val tvTemp = findViewById<TextView>(R.id.tvTemp)
        val tvHumidity = findViewById<TextView>(R.id.tvHumidity)
        val tvWindSpeed = findViewById<TextView>(R.id.tvWindSpeed)
        val tvGeoCoordinate = findViewById<TextView>(R.id.tvGeoCoordinate)

        Fuel.get("https://api.openweathermap.org/data/2.5/weather?q=Stockholm&appid=c08fe97dd09ae44d6a735e9015e56584")
            .responseString{ request, response, result ->
                val resultData = result.get()
                val gson = Gson()
                val weatherResponse = gson.fromJson(resultData,WeatherResponse::class.java)

                tvCityName.text = weatherResponse.name
                tvWeatherDescription.text = "Weather Description: ${weatherResponse.weather[0].description}"
                tvHumidity.text = "Humidity ${weatherResponse.main?.humidity}"
                tvTemp.text = "Temp ${weatherResponse.main?.temp}"
                tvWindSpeed.text = "Wind Speed: ${weatherResponse.wind?.speed}"
                tvGeoCoordinate.text = "GeoCoordinate: ${weatherResponse.coord?.lat} ${weatherResponse.coord?.lon}"
            }
    }
}

//https://api.openweathermap.org/data/2.5/weather?q=Stockholm&appid=c08fe97dd09ae44d6a735e9015e56584