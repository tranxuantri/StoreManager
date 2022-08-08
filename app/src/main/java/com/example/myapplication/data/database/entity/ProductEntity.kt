package com.example.myapplication.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.domain.model.Product
import java.util.*

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey
    val _id: UUID = UUID.randomUUID(),
    val name: String,
    val code: String,
    val price: Int,
    val unit: String,
    @ColumnInfo(name = "quantity_stock")
    val quantityInStock: Int
)

fun ProductEntity.toDomainModel() =
        Product(
                this.name,
                this.code,
                this.price,
                this.unit,
                this.quantityInStock
        )