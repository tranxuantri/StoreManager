package com.example.myapplication.db

import java.util.*

class Bill(
    val _id: Int,
    val bill_code: String,
    val date: Date,
    val customer: String,
    val totalPrice: String,
    val status: String
)