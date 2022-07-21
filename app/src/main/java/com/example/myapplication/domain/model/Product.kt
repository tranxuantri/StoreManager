package com.example.myapplication.domain.model

internal data class Product(
    val name: String,
    val code: String,
    val price: Int,
    val unit: String, val quantityInStock: Int
)