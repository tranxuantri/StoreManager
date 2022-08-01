package com.example.myapplication.utility

import com.example.myapplication.data.database.entity.ProductEntity
import com.example.myapplication.data.database.entity.toDomainModel
import com.example.myapplication.data.network.model.Product
import com.example.myapplication.data.network.model.toDomainModel

fun List<Product>.convert(): List<com.example.myapplication.domain.model.Product> {
    val listProduct: ArrayList<com.example.myapplication.domain.model.Product> = ArrayList()
    iterator().forEach { product -> listProduct.add(product.toDomainModel()) }
    return listProduct
}

@JvmName("convertProductEntity")
fun List<ProductEntity>.convert(): List<com.example.myapplication.domain.model.Product> {
    val listProduct: ArrayList<com.example.myapplication.domain.model.Product> = ArrayList()
    iterator().forEach { product -> listProduct.add(product.toDomainModel()) }
    return listProduct
}