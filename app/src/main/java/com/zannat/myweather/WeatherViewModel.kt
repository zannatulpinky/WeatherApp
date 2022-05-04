package com.zannat.myweather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson

class WeatherViewModel : ViewModel() {

    var weatherResponse = MutableLiveData<WeatherResponse>()

    fun getWeatherData(cityName: String) {
        Fuel.get("https://api.openweathermap.org/data/2.5/weather?q=${cityName}&appid=f123a9238871f71c4530e652db2c0c53")
            .responseString { request, response, result ->
                try {
                    val resultData = result.get()
                    val gson = Gson()
                    val resp = gson.fromJson(resultData, WeatherResponse::class.java)
                    weatherResponse.value = resp
                } catch (ex:Exception){

                }
            }

    }

}