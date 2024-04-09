package com.who.climasense.repository

import com.who.climasense.data.DataOrException
import com.who.climasense.models.Weather
import com.who.climasense.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {
    suspend fun getWeatherData(city: String, units: String): DataOrException<Weather, Boolean, Exception> {
        val data = DataOrException<Weather, Boolean, Exception>()
        try {
            data.data = api.getWeatherData(city, units)
        } catch (e: Exception) {
//            Log.d("WeatherRepositoryKaFaltuError", "Error: $e")
            data.exception = e
        }
        return data
    }
}
