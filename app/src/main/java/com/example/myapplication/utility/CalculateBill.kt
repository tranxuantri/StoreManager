package com.example.myapplication.utility

import com.example.myapplication.db.BillDetail

class CalculateBill {
    companion object {
        fun calculate(list: List<BillDetail>): Int {
            var sum = 0
            for (bill in list) {
                sum += bill.product_price.toInt() * bill.product_quantity.toInt()
            }
            return sum
        }
    }

}