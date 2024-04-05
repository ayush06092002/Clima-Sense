package com.who.climasense.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.who.climasense.models.Favorites
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("Select * from fav_table")
    fun getFavorites() : Flow<List<Favorites>>

    @Query("Select * from fav_table where city = :city")
    suspend fun getFavoriteById(city: String) : Favorites

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: Favorites)

    @Query("Delete from fav_table")
    suspend fun deleteAllFavorites()

    @Delete
    suspend fun deleteFavorite(favorite: Favorites)
}