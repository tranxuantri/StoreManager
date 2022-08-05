package com.example.myapplication.domain.repository

import com.example.myapplication.data.network.ResultWrapper
import com.example.myapplication.data.network.model.ProductJson
import com.example.myapplication.domain.model.Product

internal interface ProductRepository {
    suspend fun getProductList(): List<Product>

    suspend fun getProductInfo(name: String): List<Product>

    suspend fun getProductListApi():ResultWrapper<List<ProductJson>>
}