package com.who.climasense.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.who.climasense.models.Unit
import com.who.climasense.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val repository: WeatherDbRepository)
    : ViewModel(){
        private val _unitList = MutableStateFlow(emptyList<Unit>())
        val unitList = _unitList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUnit().distinctUntilChanged().collect {
                if(it.isEmpty()){
                    Log.d("SettingsViewModel", "No Units found")
                }else{
                    _unitList.value = it
//                    Log.d("FavoriteViewModel", "Unit found {$unitList}")
                }
            }
        }
    }

    fun addUnit(unit: Unit){
        viewModelScope.launch {
            repository.addUnit(unit)
        }
    }

    fun updateUnit(unit: Unit){
        viewModelScope.launch {
            repository.updateUnit(unit)
        }
    }

    fun getUnit(){
        viewModelScope.launch {
            repository.getUnit()
        }
    }

    fun deleteUnit(){
        viewModelScope.launch {
            repository.deleteUnit()
        }
    }

}