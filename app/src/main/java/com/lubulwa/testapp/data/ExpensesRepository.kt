package com.lubulwa.testapp.data

import com.lubulwa.testapp.data.dao.ExpensesDao
import com.lubulwa.testapp.model.Expense
import javax.inject.Inject

class ExpensesRepository @Inject constructor(val expensesDao: ExpensesDao) {

    fun getExpenses() {

    }

    fun updateExpense(expense: Expense) {

    }

    fun deleteExpense(expense: Expense) {

    }

    fun addExpense(expense: Expense) {

    }

}