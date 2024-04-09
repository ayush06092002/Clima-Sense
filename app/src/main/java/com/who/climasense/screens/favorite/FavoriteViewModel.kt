package com.who.climasense.screens.favorite

import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.who.climasense.models.Favorites
import com.who.climasense.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: WeatherDbRepository)
    : ViewModel() {
    private val _favList = MutableStateFlow<List<Favorites>>(emptyList())
    val favList = _favList.asStateFlow()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavorites().distinctUntilChanged().collect {
                if(it.isEmpty()){
                    Log.d("FavoriteViewModel", "No favorites found")
                }else{
                    _favList.value = it
//                    Log.d("FavoriteViewModel", "Favorites found {$favList}")
                }
            }
        }
    }

    fun addFavorite(favorite: Favorites){
        viewModelScope.launch {
            repository.addFavorite(favorite)
        }
    }
    fun deleteFavorite(city: String){
        viewModelScope.launch {
            repository.deleteFavorite(city)
        }
    }
    fun deleteAllFavorites(){
        viewModelScope.launch {
            repository.deleteAllFavorites()
        }
    }
    fun getFavoriteById(city: String){
        viewModelScope.launch {
            repository.getFavoriteById(city)
        }
    }

    fun isFavorite(city: String): Boolean {
        return favList.value.any { it.city == city }
    }
}