package com.lubulwa.testapp.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lubulwa.testapp.data.ExpensesRepository
import com.lubulwa.testapp.model.Expense
import com.lubulwa.testapp.utils.State
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val expensesRepository: ExpensesRepository)  : ViewModel() {

    private val _getExpensesLiveData = MutableLiveData<State<List<Expense>>>()
    private val _addExpensesLiveData = MutableLiveData<State<Long>>()
    private val _updateExpensesLiveData = MutableLiveData<State<Int>>()
    private val _deleteExpensesLiveData = MutableLiveData<State<Int>>()

    val addExpensesLiveData: LiveData<State<Long>>
        get() = _addExpensesLiveData

    val getExpensesLiveData: LiveData<State<List<Expense>>>
        get() = _getExpensesLiveData

    val updateExpensesLiveData: LiveData<State<Int>>
        get() = _updateExpensesLiveData

    val deleteExpensesLiveData: LiveData<State<Int>>
        get() = _deleteExpensesLiveData

    fun getExpenses() {
        viewModelScope.launch {
            expensesRepository.getExpenses().collect {
                _getExpensesLiveData.value = it
            }
        }
    }

    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            expensesRepository.addExpense(expense).collect {
                _addExpensesLiveData.value = it
            }
        }
    }

    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            expensesRepository.deleteExpense(expense).collect {
                _deleteExpensesLiveData.value = it
            }
        }
    }

    fun updateExpense(expense: Expense) {
        viewModelScope.launch {
            expensesRepository.updateExpense(expense).collect {
                _updateExpensesLiveData.value = it
            }
        }
    }

}