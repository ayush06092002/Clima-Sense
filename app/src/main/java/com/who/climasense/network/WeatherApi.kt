package com.who.climasense.network

import com.who.climasense.models.Weather
import com.who.climasense.utils.Constants

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET(value = "data/2.5/forecast?")
    suspend fun getWeatherData(
        @Query("q") city: String,
        @Query("units") units: String = "metric",
        @Query("appid") appid: String = Constants.API_KEY
    ): Weather
}