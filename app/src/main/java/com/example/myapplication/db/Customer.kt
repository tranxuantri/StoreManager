package com.example.myapplication.db

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "customer")
data class Customer(
    @PrimaryKey
    @NonNull
    val _id: UUID = UUID.randomUUID(),
    val name: String,
    val phone: String
)