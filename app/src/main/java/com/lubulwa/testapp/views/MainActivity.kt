package com.lubulwa.testapp.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.lubulwa.testapp.R
import com.lubulwa.testapp.base.BaseActivity
import com.lubulwa.testapp.databinding.ActivityMainBinding
import com.lubulwa.testapp.model.Expense
import com.lubulwa.testapp.utils.State
import com.lubulwa.testapp.utils.viewModelOf
import com.lubulwa.testapp.views.edit.EditExpenseActivity
import java.nio.file.Files.delete

class MainActivity : BaseActivity<HomeViewModel, ActivityMainBinding>(),
    ExpensesAdapter.OnItemClickListener, ExpensesAdapter.OnItemLongClickListener {

    private val TAG = MainActivity::class.java.simpleName

    private val mAdapter: ExpensesAdapter by lazy { ExpensesAdapter(this, this) }

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
                    getExpenses()
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

    private fun deleteExpenseConfirm(expense: Expense) {
        MaterialDialog(this).show {
            title(R.string.confirm_delete)
            message(R.string.delete_expense_message)
            positiveButton(R.string.delete) {
                mViewModel.deleteExpense(expense)
                Toast.makeText(this@MainActivity, "Expense deleted!", Toast.LENGTH_LONG).show()
            }
            negativeButton(R.string.cancel)
        }
    }

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun getViewModel(): HomeViewModel = viewModelOf(mViewModelProvider)

    override fun onResume() {
        super.onResume()
        getExpenses()
    }

    override fun onItemClicked(expense: Expense) {
        Intent(this, EditExpenseActivity::class.java).also {
            it.putExtra(EditExpenseActivity.EXPENSE_INTENT, expense)
            startActivity(it)
        }
    }

    override fun onItemLongClicked(expense: Expense) {
        MaterialDialog(this).show {
            title(R.string.select_action)
            listItems(R.array.list_options) { dialog, index, text ->
                when(index) {
                    0 -> deleteExpenseConfirm(expense)
                }
            }
        }
    }
}