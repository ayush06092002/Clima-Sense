package com.who.climasense.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.who.climasense.models.Favorites


@Database(entities = [Favorites::class], version = 1, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase(){
    abstract fun weatherDao(): WeatherDao
}
