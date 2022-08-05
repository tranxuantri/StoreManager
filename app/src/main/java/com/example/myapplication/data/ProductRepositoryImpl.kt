package com.example.myapplication.data

import com.example.myapplication.data.database.AppDatabase
import com.example.myapplication.data.database.ProductDAO
import com.example.myapplication.data.network.FirebaseService
import com.example.myapplication.data.network.ProductApiService
import com.example.myapplication.data.network.ResultWrapper
import com.example.myapplication.data.network.SafeApi
import com.example.myapplication.data.network.model.ProductJson
import com.example.myapplication.domain.model.Product
import com.example.myapplication.domain.repository.ProductRepository
import com.example.myapplication.utility.convert
import java.net.UnknownHostException

internal class ProductRepositoryImpl(
//    private val firebaseService: FirebaseService,
//    private val appDatabase: AppDatabase
    private val productApiService: ProductApiService
) :SafeApi(),ProductRepository {
    override suspend fun getProductList(): List<Product> {
//        return try {
//            firebaseService.getListProduct().convert()
//            productApiService.getProductList().convert()
//        } catch (e: UnknownHostException) {
//            appDatabase.productDao().getAll().convert()
           return ArrayList()
//        }
    }

    override suspend fun getProductInfo(name: String): List<Product> {
        return try {
//            firebaseService.getProducts(name).convert()
            productApiService.getProduct(name).convert()
        } catch (e: UnknownHostException) {
//            appDatabase.productDao().getProducts(name).convert()
            ArrayList()
        }
    }

    override suspend fun getProductListApi(): ResultWrapper<List<ProductJson>> {
        TODO("Not yet implemented")
    }

//    override suspend fun getProductListApi(): ResultWrapper<List<ProductJson>> =
//        safeApiCall { productApiService.getProductList() }

}
