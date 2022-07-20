package com.example.myapplication.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
class Product(
    @PrimaryKey(autoGenerate = true)
    val _id: Int,
    val name: String,
    val code: String,
    val price: Int,
    val unit: String,
    val quantityInStock: Int
)