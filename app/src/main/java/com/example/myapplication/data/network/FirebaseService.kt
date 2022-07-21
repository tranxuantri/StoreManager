package com.example.myapplication.data.network

import android.content.Context
import com.example.myapplication.data.database.AppDatabase
import com.google.firebase.database.FirebaseDatabase

class FirebaseService {

    private lateinit var mContext: Context

    fun newInstance(context: Context): FirebaseService {
        mContext = context
        return FirebaseService()
    }

    fun syncData() {
        val database = AppDatabase.newInstance(mContext)
        val billList = database.billDao().getAll()
        val billDetailList = database.billDetailDao().getAll()
        val productList = database.productDao().getAll()

        val firebaseDatabase = FirebaseDatabase.getInstance()
        val billRef = firebaseDatabase.getReference("bill")
        billRef.setValue(billList)
        val billDetailRef = firebaseDatabase.getReference("bill_detail")
        billDetailRef.setValue(billDetailList)
        val producRef = firebaseDatabase.getReference("product")
        producRef.setValue(productList)
    }
}