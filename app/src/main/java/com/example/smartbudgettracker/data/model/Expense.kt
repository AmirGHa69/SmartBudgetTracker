package com.example.smartbudgettracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val amount: Double,
    val category: String,
    val description: String,
    val timestamp: Long = System.currentTimeMillis()
)
