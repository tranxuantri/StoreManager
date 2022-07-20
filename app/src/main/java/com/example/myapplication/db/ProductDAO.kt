package com.example.myapplication.db

import androidx.room.*

@Dao
interface ProductDAO {
    @Query("SELECT * FROM product")
    fun getAll(): List<Product>

    @Query("SELECT  * FROM product WHERE code IN (:barcode)")
    fun getProduct(barcode: Int)

    @Insert
    fun insertProduct(product: Product)

    @Update
    fun updateProduct(product: Product)

}