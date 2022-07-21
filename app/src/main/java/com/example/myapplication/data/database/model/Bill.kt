package com.example.myapplication.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "bill")
data class Bill(
    @PrimaryKey
    val _id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "bill_code")
    val billCode: String = "",
    val date: String = "",
    val customer: String = "",
    @ColumnInfo(name = "total_price")
    val totalPrice: String = "",
    val status: String = ""
) {
    constructor(customer: String, date: String, totalPrice: String) : this()
}