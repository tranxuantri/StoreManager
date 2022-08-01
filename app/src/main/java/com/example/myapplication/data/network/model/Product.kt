package com.example.myapplication.data.network.model

import androidx.room.ColumnInfo

data class Product(
    val _id: Int,
    val name: String,
    val code: String,
    val price: Int,
    val unit: String,
    @ColumnInfo(name = "quantity_stock")
    val quantityInStock: Int
)


internal fun Product.toDomainModel() =
    com.example.myapplication.domain.model.Product(
        this.name,
        this.code,
        this.price,
        this.unit,
        this.quantityInStock
    )