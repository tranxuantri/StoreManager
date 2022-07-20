package com.example.myapplication.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "bill")
data class Bill(
    @PrimaryKey(autoGenerate = true)
    val _id: Int,
    @ColumnInfo(name = "bill_code")
    val billCode: String,
    val date: Date,
    val customer: String,
    @ColumnInfo(name = "total_price")
    val totalPrice: String,
    val status: String
)