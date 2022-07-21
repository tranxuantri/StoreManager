package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.Product

internal interface ProductRepository {
    suspend fun getProductList(): List<Product>

    suspend fun getProductInfo(code: String): Product
}