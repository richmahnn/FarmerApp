package com.lubulwa.testapp.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lubulwa.testapp.data.ExpensesRepository
import com.lubulwa.testapp.model.Expense
import com.lubulwa.testapp.utils.State
import javax.inject.Inject

class HomeViewModel @Inject constructor(val expensesRepository: ExpensesRepository)  : ViewModel() {

    private val _getExpensesLiveData = MutableLiveData<State<List<Expense>>>()

    val getExpensesLiveData: LiveData<State<List<Expense>>>
        get() = _getExpensesLiveData

}