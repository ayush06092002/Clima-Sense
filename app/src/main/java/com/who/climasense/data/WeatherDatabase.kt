package com.who.climasense.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.who.climasense.models.Favorites
import com.who.climasense.models.Unit


@Database(entities = [Favorites::class, Unit::class], version = 2, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase(){
    abstract fun weatherDao(): WeatherDao
}
