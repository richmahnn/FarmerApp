package com.lubulwa.testapp.views.edit

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.lubulwa.testapp.base.BaseActivity
import com.lubulwa.testapp.databinding.ActivityEditExpenseBinding
import com.lubulwa.testapp.model.Expense
import com.lubulwa.testapp.utils.State
import com.lubulwa.testapp.utils.viewModelOf
import com.lubulwa.testapp.views.HomeViewModel

class EditExpenseActivity : BaseActivity<HomeViewModel, ActivityEditExpenseBinding>() {

    private val TAG = EditExpenseActivity::class.java.simpleName

    companion object {
        const val EXPENSE_INTENT = "expense_intent"
    }

    private lateinit var passedExpense: Expense

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)

        initIntent()
        initViewModel()
        setupViews()
    }

    private fun initIntent() {
        passedExpense = intent.getSerializableExtra(EXPENSE_INTENT) as Expense
    }

    private fun initViewModel() {
        mViewModel.updateExpensesLiveData.observe(this, Observer {
            when (it) {
                is State.Success -> {
                    Log.e(TAG, "Expense updated ${it.data}")
                    Toast.makeText(this, "Expense updated", Toast.LENGTH_LONG).show()
                }
                is State.Error -> {
                    Log.e(TAG, it.message)
                }
            }
        })
    }

    private fun setupViews() {
        mViewBinding.etExpenseAmount.setText(passedExpense.amount.toString())
        mViewBinding.etExpenseDesc.setText(passedExpense.description)


        mViewBinding.btnUpdate.setOnClickListener {
            val description = mViewBinding.etExpenseDesc.text.toString()
            val amount = mViewBinding.etExpenseAmount.text.toString()

            if (description.isNotEmpty() || amount.isNotEmpty()) {
                val expense = Expense(passedExpense.id, description, amount.toDouble(), passedExpense.timestamp)
                mViewModel.updateExpense(expense)
            }
        }
    }

    override fun getViewBinding(): ActivityEditExpenseBinding = ActivityEditExpenseBinding.inflate(layoutInflater)

    override fun getViewModel(): HomeViewModel = viewModelOf(mViewModelProvider)
}