package com.example.myapplication.data.database

import androidx.room.*
import com.example.myapplication.data.database.model.Product

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