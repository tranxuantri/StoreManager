package com.example.myapplication.data.network.model

import com.google.gson.annotations.SerializedName

data class ProductJson(
    val _id: String,
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

internal fun ProductJson.toDomainModel() =
    com.example.myapplication.domain.model.Product(
        this.name,
        this.code,
        this.price,
        this.unit,
        this.quantityInStock
    )