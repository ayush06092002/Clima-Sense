package com.who.climasense.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "fav_table")
data class Favorites(
    @NotNull
    @PrimaryKey
    @ColumnInfo(name = "city")
    val city: String,

    @ColumnInfo(name = "temp")
    val temp: String,

    @ColumnInfo(name = "icon")
    val icon: Int
)
