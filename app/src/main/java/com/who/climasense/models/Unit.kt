package com.who.climasense.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "settings")
data class Unit(
    @PrimaryKey
    @ColumnInfo(name = "unit")
    val unit: String,
    @ColumnInfo(name = "defaultCity")
    val defaultCity: String
)
