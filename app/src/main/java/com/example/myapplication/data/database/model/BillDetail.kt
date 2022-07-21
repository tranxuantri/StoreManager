package com.example.myapplication.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "bill_detail")
class BillDetail(
    val _id: Int,
    @ColumnInfo(name = "bill_code")
    val billCode: String,
    @ColumnInfo(name = "product_code")
    val productCode: String,
    @ColumnInfo(name = "product_price")
    val productPrice: String,
    @ColumnInfo(name = "product_quantity")
    val product_quantity: String
)