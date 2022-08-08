package com.example.myapplication.data.database

import androidx.room.*
import com.example.myapplication.data.database.entity.ProductEntity

@Dao
interface ProductDAO {
    @Query("SELECT * FROM product")
    suspend fun getAll(): List<ProductEntity>

    @Query("SELECT  * FROM product WHERE name IN (:name)")
    fun getProducts(name: String): List<ProductEntity>

    @Insert
    fun insertProduct(product: ProductEntity)

    @Update
    fun updateProduct(product: ProductEntity)

//    @Delete
//    fun deleteAll()

}