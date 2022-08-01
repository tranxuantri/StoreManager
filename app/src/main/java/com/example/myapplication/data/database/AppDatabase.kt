package com.example.myapplication.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun newInstance(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "store-manager").build()
    }

    internal abstract fun productDao(): ProductDAO
    abstract fun billDetailDao() : BillDetailDAO
    abstract fun billDao() : BillDAO

}