package com.app.schoolapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.schoolapplication.databinding.SchoolListViewItemBinding
import com.app.schoolapplication.domain.model.School


class SchoolListItemAdapter(private val schoolClickListener: SchoolClickListener) :
    ListAdapter<School, SchoolListItemAdapter.StocksItemViewHolder>(DiffCallback) {



    class StocksItemViewHolder(
        private var binding: SchoolListViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(school: School, schoolClickListener: SchoolClickListener) {
            binding.school = school
            binding.clickListener = schoolClickListener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StocksItemViewHolder {
        return StocksItemViewHolder(
            SchoolListViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: StocksItemViewHolder, position: Int) {
        getItem(position)?.let { school ->
            holder.bind(school, schoolClickListener)
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<School>() {
        override fun areItemsTheSame(oldItem: School, newItem: School): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: School, newItem: School): Boolean {
            return oldItem.schoolName == newItem.schoolName
        }
    }
}

class SchoolClickListener(val clickListener: (school: School) -> Unit) {
    fun onClick(school: School) = clickListener(school)
}

