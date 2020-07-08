package com.lubulwa.testapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val description: String,
    val amount: Double,
    val timestamp: Long
)