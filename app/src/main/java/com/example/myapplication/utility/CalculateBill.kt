package com.example.myapplication.utility

import com.example.myapplication.data.database.model.BillDetail

class CalculateBill {
    companion object {
        fun calculate(list: List<BillDetail>): Int {
            var sum = 0
            for (bill in list) {
                sum += bill.productPrice.toInt() * bill.productQuantity.toInt()
            }
            return sum
        }
    }

}