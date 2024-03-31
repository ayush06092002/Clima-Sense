package com.who.climasense.screens.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.who.climasense.data.DataOrException
import com.who.climasense.models.Weather
import com.who.climasense.repository.WeatherRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val apiRepository: WeatherRepository) : ViewModel(){
    val data: MutableState<DataOrException<Weather, Boolean, Exception>>
    = mutableStateOf(DataOrException(null, false, Exception("")))

    init {
        loadWeather()
    }

    private fun loadWeather() {
        getWeather("Kanpur", "metric")
    }

    private fun getWeather(city: String, metric: String) {
        viewModelScope.launch {
            if(city.isEmpty())
                return@launch
            data.value.isLoading = true
            data.value = apiRepository.getWeatherData(city, metric)
            if(data.value.data.toString().isNotEmpty())
                data.value.isLoading = false
        }

        Log.d("MainViewModel", "Data: ${data.value.data.toString()}")
    }
}