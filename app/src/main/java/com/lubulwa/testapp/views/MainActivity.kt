package com.lubulwa.testapp.views

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lubulwa.testapp.base.BaseActivity
import com.lubulwa.testapp.databinding.ActivityMainBinding
import com.lubulwa.testapp.model.Expense
import com.lubulwa.testapp.utils.State
import com.lubulwa.testapp.utils.viewModelOf

class MainActivity : BaseActivity<HomeViewModel, ActivityMainBinding>() {

    private val TAG = MainActivity::class.java.simpleName

    private val mAdapter: ExpensesAdapter by lazy { ExpensesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)

        initViewModel()
        setupViews()
    }

    private fun initViewModel() {
        mViewModel.addExpensesLiveData.observe(this, Observer {
            when (it) {
                is State.Success -> {
                    Log.e(TAG, "Expense added")
                    getExpenses()
                }
                is State.Error -> {
                    Log.e(TAG, it.message)
                }
            }
        })

        mViewModel.updateExpensesLiveData.observe(this, Observer {
            when (it) {
                is State.Success -> {
                    Log.e(TAG, "Expense updated")
                }
                is State.Error -> {
                    Log.e(TAG, it.message)
                }
            }
        })

        mViewModel.deleteExpensesLiveData.observe(this, Observer {
            when (it) {
                is State.Success -> {
                    Log.e(TAG, "Expense deleted")
                }
                is State.Error -> {
                    Log.e(TAG, it.message)
                }
            }
        })

        mViewModel.getExpensesLiveData.observe(this, Observer {
            when (it) {
                is State.Success -> {
                    Log.e(TAG, "Expenses fetched ${it.data.toString()}")
                    var total = 0
                    it.data.forEach {
                        total += it.amount.toInt()
                    }
                    mViewBinding.tvTotalExpenses.text = total.toString()

                    mAdapter.submitList(it.data.toMutableList())
                }
                is State.Error -> {
                    Log.e(TAG, it.message)
                }
            }
        })
    }

    private fun getExpenses() {
        mViewModel.getExpenses()
    }

    private fun setupViews() {
        mViewBinding.rvExpenses.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mAdapter
        }


        mViewBinding.btnSave.setOnClickListener {
            val description = mViewBinding.etExpenseDesc.text.toString()
            val amount = mViewBinding.etExpenseAmount.text.toString()

            if (description.isNotEmpty() && amount.isNotEmpty()) {
                val expense = Expense(0, description, amount.toDouble(), System.currentTimeMillis())
                mViewModel.addExpense(expense)
            }
        }
    }

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun getViewModel(): HomeViewModel = viewModelOf(mViewModelProvider)

    override fun onResume() {
        super.onResume()
        getExpenses()
    }
}