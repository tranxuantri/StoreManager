package com.example.myapplication.db

import androidx.room.*

interface KalixShoesDAO {
    @Insert
    fun insert(kalixShoe: KalixShoes)

    @Update
    fun update(kalixShoe: KalixShoes)

    @Delete
    fun delete(kalixShoe: KalixShoes)

    @Query("DELETE FROM kalix_shoes")
    fun deleteTable()

    @get:Query("SELECT * FROM kalix_shoes")
    val getListShoesFromDB: List<KalixShoes?>?

}