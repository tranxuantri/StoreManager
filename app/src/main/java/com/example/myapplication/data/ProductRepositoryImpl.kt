package com.example.myapplication.data

import com.example.myapplication.data.database.ProductDAO
import com.example.myapplication.data.network.FirebaseService
import com.example.myapplication.domain.model.Product
import com.example.myapplication.domain.repository.ProductRepository
import com.example.myapplication.utility.convert
import java.net.UnknownHostException

internal class ProductRepositoryImpl(
    private val firebaseService: FirebaseService,
    private val productDAO: ProductDAO
) : ProductRepository {
    override suspend fun getProductList(): List<Product> {
        return try {
            firebaseService.getListProduct().convert()
        } catch (e: UnknownHostException) {
            productDAO.getAll().convert()
        }
    }

    override suspend fun getProductInfo(name: String): List<Product> {
        return try {
            firebaseService.getProducts(name).convert()
        } catch (e: UnknownHostException) {
            productDAO.getProducts(name).convert()
        }
    }
}
