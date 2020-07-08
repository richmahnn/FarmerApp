package com.lubulwa.testapp.views

import androidx.recyclerview.widget.RecyclerView
import com.lubulwa.testapp.databinding.ItemExpenseBinding
import com.lubulwa.testapp.model.Expense
import com.lubulwa.testapp.utils.getTimeFromTimestamp

class ExpenseViewHolder(private val binding: ItemExpenseBinding): RecyclerView.ViewHolder(binding.root) {


    fun bind(
        expense: Expense,
        onItemClickListener: ExpensesAdapter.OnItemClickListener? = null,
        onItemLongClickListener: ExpensesAdapter.OnItemLongClickListener? = null)
    {
        binding.tvExpenseDesc.text = expense.description
        binding.tvExpenseAmount.text = expense.amount.toInt().toString()
        binding.tvDate.text = getTimeFromTimestamp(expense.timestamp)

        onItemClickListener?.let { listener ->
            binding.root.setOnClickListener { listener.onItemClicked(expense) }
        }

        onItemLongClickListener?.let { listener ->
            binding.root.setOnLongClickListener {
                listener.onItemLongClicked(expense)
                true
            }
        }
    }

}