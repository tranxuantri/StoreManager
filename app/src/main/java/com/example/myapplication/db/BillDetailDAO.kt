package com.example.myapplication.db

import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface BillDetailDAO {
    @Query("SELECT * FROM bill_detail")
    fun getAll():List<BillDetail>

    @Query("SELECT * FROM bill_detail WHERE bill_code IN (:billCode)")
    fun getBillForCustomer(billCode: String)

    @Insert
    fun insertBillDetail(billDetail: BillDetail)

    @Update
    fun updateBillDetail(bill: BillDetail)
}