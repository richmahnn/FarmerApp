package com.lubulwa.testapp.data

import com.lubulwa.testapp.data.dao.ExpensesDao
import com.lubulwa.testapp.model.Expense
import com.lubulwa.testapp.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ExpensesRepository @Inject constructor(val expensesDao: ExpensesDao) {

    fun getExpenses(): Flow<State<List<Expense>>> {

        return object : LocalBoundRepository<List<Expense>, Expense>() {

            override suspend fun saveData(data: Expense): List<Expense>? = null

            override fun fetchFromLocal(): Flow<List<Expense>>? = expensesDao.getExpenses()

            override suspend fun dataToSave(): Expense? = null
        }.asFlow().flowOn(Dispatchers.IO)

    }

    fun updateExpense(expense: Expense): Flow<State<Int>> {
        return object : LocalBoundRepository<Int, Expense>() {
            override suspend fun saveData(data: Expense): Int? = expensesDao.updateExpense(data)

            override fun fetchFromLocal(): Flow<Int>? = null

            override suspend fun dataToSave(): Expense? = expense

        }.asFlow().flowOn(Dispatchers.IO)
    }

    fun deleteExpense(expense: Expense): Flow<State<Int>> {
        return object : LocalBoundRepository<Int, Expense>() {
            override suspend fun saveData(data: Expense): Int? = expensesDao.deleteExpense(data)

            override fun fetchFromLocal(): Flow<Int>?  = null

            override suspend fun dataToSave(): Expense? = expense


        }.asFlow().flowOn(Dispatchers.IO)
    }

    fun addExpense(expense: Expense): Flow<State<Long>> {
        return object : LocalBoundRepository<Long, Expense>() {
            override suspend fun saveData(data: Expense): Long? = expensesDao.addExpense(data)
            override fun fetchFromLocal(): Flow<Long>? = null

            override suspend fun dataToSave(): Expense? = expense

        }.asFlow().flowOn(Dispatchers.IO)
    }

}