package com.example.myapplication.db

import android.content.Context
import android.os.Bundle
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun newInstance(context: Context): KalixShoesDAO? =
            Room.databaseBuilder(context, AppDatabase::class.java, "db-kalix-shoes")
                .allowMainThreadQueries().build().kalixShoesDAO

    }

    abstract val kalixShoesDAO: KalixShoesDAO?
}