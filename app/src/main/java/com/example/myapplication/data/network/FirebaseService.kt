package com.example.myapplication.data.network

import com.example.myapplication.data.network.model.ProductJson
import com.example.myapplication.utility.convertToList
import com.google.firebase.database.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import timber.log.Timber
import java.lang.reflect.Type
import java.util.ArrayList
import java.util.HashMap

internal class FirebaseService {

    private lateinit var databaseReference: DatabaseReference

    companion object {
        fun newInstance(): FirebaseService {
            return FirebaseService()
        }
    }

//    suspend fun syncData() {
//        val database = AppDatabase.newInstance(mContext)
//        val billList = database.billDao().getAll()
//        val billDetailList = database.billDetailDao().getAll()
//        val productList = database.productDao().getAll()

//        val firebaseDatabase = FirebaseDatabase.getInstance()
//        val billRef = firebaseDatabase.getReference("bill")
//        billRef.setValue(billList)
//        val billDetailRef = firebaseDatabase.getReference("bill_detail")
//        billDetailRef.setValue(billDetailList)
//        val producRef = firebaseDatabase.getReference("product")
//        producRef.setValue(productList)
//    }

    suspend fun getListProduct(): List<ProductJson> {
        databaseReference = FirebaseDatabase.getInstance().getReference("product")
        var listProductJson: Map<String,ProductJson> = HashMap()

        // Read from the real-time database
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val typeListOfProducts: Type = object : TypeToken<Map<String, ProductJson?>?>() {}.type
                val value = dataSnapshot.getValue(Any::class.java)
                val json: String = Gson().toJson(value)
                listProductJson = Gson().fromJson(json, typeListOfProducts)
                Timber.tag("c").d("Value is: %s", listProductJson)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Timber.tag("TAG").w(error.toException(), "Failed to read value.")
            }
        })

        return listProductJson.convertToList()
    }

    fun getProducts(name: String): ArrayList<ProductJson> {
        val productJsons: ArrayList<ProductJson> = ArrayList()
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (i in dataSnapshot.children) {
                        val productJson = i.getValue(ProductJson::class.java)
                        if (productJson!!.name == name) {
                            productJsons.add(productJson)
                        }
                    }
                } else {
                    onCancelled(DatabaseError.fromException(Exception("Not have data")))
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Timber.e(error.toException())
            }
        })
        return productJsons
    }
}