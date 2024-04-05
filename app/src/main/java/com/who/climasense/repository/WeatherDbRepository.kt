package com.who.climasense.repository

import com.who.climasense.data.WeatherDao
import com.who.climasense.models.Favorites
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(private val weatherDao: WeatherDao){
    fun getFavorites() : Flow<List<Favorites>> = weatherDao.getFavorites()
    suspend fun getFavoriteById(city: String) : Favorites = weatherDao.getFavoriteById(city)
    suspend fun addFavorite(favorite: Favorites) = weatherDao.addFavorite(favorite)
    suspend fun deleteAllFavorites() = weatherDao.deleteAllFavorites()
    suspend fun deleteFavorite(favorite: String) = weatherDao.deleteFavorite(favorite)

}