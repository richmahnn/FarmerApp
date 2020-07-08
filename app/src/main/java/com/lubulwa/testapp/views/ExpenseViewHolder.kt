package com.lubulwa.testapp.views

import androidx.recyclerview.widget.RecyclerView
import com.lubulwa.testapp.databinding.ItemExpenseBinding
import com.lubulwa.testapp.model.Expense

class ExpenseViewHolder(private val binding: ItemExpenseBinding): RecyclerView.ViewHolder(binding.root) {


    fun bind(expense: Expense) {
        binding.tvExpenseDesc.text = expense.description
        binding.tvExpenseAmount.text = expense.amount.toInt().toString()
    }

}