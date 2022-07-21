package com.example.myapplication.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kalix_shoes")
class KalixShoes (
    @PrimaryKey(autoGenerate = true)
    val _id: Int = 0,
    val name:String,
    val size: String,
    val color: String
)