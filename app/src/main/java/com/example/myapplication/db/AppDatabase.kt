package com.example.myapplication.db

import android.content.Context
import android.os.Bundle
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun newInstance(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "store-manager").build()
    }

    abstract fun productDao(): ProductDAO
    abstract fun billDetailDao() : BillDetailDAO
    abstract fun billDao() : BillDAO

}