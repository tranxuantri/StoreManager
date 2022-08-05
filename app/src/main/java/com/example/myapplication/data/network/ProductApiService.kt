package com.example.myapplication.data.network

import com.example.myapplication.data.network.model.ProductJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface ProductApiService {
    @GET("product.json")
    fun getProductList(): Call<Map<String,ProductJson>>

    @GET("product/")
    suspend fun getProduct(@Query("product_name") name: String = ""): List<ProductJson>

    @PUT("product")
    suspend fun addProduct(productJson: ProductJson)
}