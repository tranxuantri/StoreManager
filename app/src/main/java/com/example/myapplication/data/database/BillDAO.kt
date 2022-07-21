package com.example.myapplication.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.data.database.model.Bill

@Dao
interface BillDAO {
    @Query("SELECT * FROM bill")
    fun getAll():List<Bill>

    @Query("SELECT * FROM bill WHERE customer IN (:customerName)")
    fun getBillForCustomer(customerName: String)

    @Insert
    fun insertBill(bill: Bill)

    @Update
    fun updateBill(bill: Bill)
}