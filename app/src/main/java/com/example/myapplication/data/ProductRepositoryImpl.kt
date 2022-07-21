package com.example.myapplication.data

import com.example.myapplication.domain.model.Product
import com.example.myapplication.domain.repository.ProductRepository

class ProductRepositoryImpl:ProductRepository {
    override suspend fun getProductList(): List<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun getProductInfo(code: String): Product {
        TODO("Not yet implemented")
    }
}