package com.who.climasense.screens.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.who.climasense.data.DataOrException
import com.who.climasense.models.Weather
import com.who.climasense.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class MainViewModel @Inject constructor(private val apiRepository: WeatherRepository) : ViewModel(){
    suspend fun getWeatherData(city: String, units: String) : DataOrException<Weather, Boolean, Exception> {
        return apiRepository.getWeatherData(city, units)
    }
}