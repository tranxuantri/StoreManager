package com.example.myapplication.utility

import com.example.myapplication.data.database.entity.ProductEntity
import com.example.myapplication.data.database.entity.toDomainModel
import com.example.myapplication.data.network.model.ProductJson
import com.example.myapplication.data.network.model.toDomainModel
import com.example.myapplication.domain.model.Product

fun List<ProductJson>.convert(): List<Product> {
    val listProduct: ArrayList<Product> = ArrayList()
    iterator().forEach { product -> listProduct.add(product.toDomainModel()) }
    return listProduct
}

@JvmName("convertProductEntity")
fun List<ProductEntity>.convert(): List<Product> {
    val listProduct: ArrayList<Product> = ArrayList()
    iterator().forEach { product -> listProduct.add(product.toDomainModel()) }
    return listProduct
}

fun Map<String,ProductJson>.convertToList(): List<ProductJson> {
    val listProduct:MutableList<ProductJson> = ArrayList()
    iterator().forEach { unit ->
        run {
            val value = unit.value
            val product =
                ProductJson(unit.key,value.name, value.code, value.price, value.unit, value.quantityInStock)
            listProduct.add(product)
        }
    }
    return listProduct
}