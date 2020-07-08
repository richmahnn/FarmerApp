package com.lubulwa.testapp.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.lubulwa.testapp.databinding.ItemExpenseBinding
import com.lubulwa.testapp.model.Expense

class ExpensesAdapter(private val onItemClickListener: OnItemClickListener,
                      private val onItemLongClickListener: OnItemLongClickListener
) : ListAdapter<Expense, ExpenseViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder =
        ExpenseViewHolder (
            ItemExpenseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )


    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) = holder.bind(getItem(position), onItemClickListener, onItemLongClickListener)

    interface OnItemClickListener {
        fun onItemClicked(expense: Expense)
    }

    interface OnItemLongClickListener {
        fun onItemLongClicked(expense: Expense)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Expense>() {
            override fun areItemsTheSame(oldItem: Expense, newItem: Expense): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Expense, newItem: Expense): Boolean {
                return oldItem == newItem
            }

        }
    }
}