package com.example.myapplication.domain.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("product_name")
    val name: String,
    @SerializedName("product_code")
    val code: String,
    @SerializedName("product_price")
    val price: Int,
    val unit: String,
    @SerializedName("quantity_in_stock")
    val quantityInStock: Int

)