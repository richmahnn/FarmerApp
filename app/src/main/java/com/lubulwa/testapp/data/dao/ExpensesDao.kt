package com.lubulwa.testapp.data.dao

import androidx.room.*
import com.lubulwa.testapp.model.Expense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpensesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addExpense(expense: Expense): Long

    @Update
    fun updateExpense(expense: Expense): Int

    @Query("SELECT * FROM Expenses")
    fun getExpenses(): Flow<List<Expense>>

    @Delete
    fun deleteExpense(expense: Expense): Int

}