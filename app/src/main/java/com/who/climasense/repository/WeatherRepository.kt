package com.who.climasense.repository

import com.who.climasense.data.DataOrException
import com.who.climasense.models.Weather
import com.who.climasense.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api : WeatherApi) {
    suspend fun getWeatherData(city: String, metric: String) : DataOrException<Weather, Boolean, Exception>{
        return try {
            val response = api.getWeatherData(city, units = metric)
            DataOrException(data = response)
        } catch (e: Exception) {
            DataOrException(exception = e)
        }
    }
}