package com.who.climasense.screens.main

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.who.climasense.data.DataOrException
import com.who.climasense.models.Weather
import com.who.climasense.repository.WeatherRepository
import dagger.Provides
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiRepository: WeatherRepository,
    private val sharedPreferences: SharedPreferences) : ViewModel(){
    private val gson = Gson()

    fun saveWeatherData(weatherData: Weather) {
        val json = gson.toJson(weatherData)
        sharedPreferences.edit().putString("weather_data", json).apply()
    }

    fun getLastSavedWeatherData(): Weather? {
        val json = sharedPreferences.getString("weather_data", null)
        return if (json != null) {
            gson.fromJson(json, Weather::class.java)
        } else {
            null
        }
    }
    suspend fun getWeatherData(city: String, units: String) : DataOrException<Weather, Boolean, Exception> {
        return apiRepository.getWeatherData(city, units)
    }
}