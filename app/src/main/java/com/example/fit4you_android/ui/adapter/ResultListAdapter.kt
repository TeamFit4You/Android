package com.example.fit4you_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fit4you_android.data.model.FitResult
import com.example.fit4you_android.databinding.ItemTodayFitResultBinding
import com.example.fit4you_android.ui.view.today.start.ResultActivity
import com.example.fit4you_android.ui.view.today.start.ResultViewModel

class ResultListAdapter(
    private val context: ResultActivity,
    private val recommendListViewModel: ResultViewModel,
    private val lists: List<FitResult>
) : RecyclerView.Adapter<ResultListAdapter.ResultListViewHolder>() {

    inner class ResultListViewHolder(
        private val binding: ItemTodayFitResultBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FitResult) {
            binding.result = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val listItemBinding = ItemTodayFitResultBinding.inflate(inflater, parent, false)
        return ResultListViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: ResultListViewHolder, position: Int) {
        holder.bind(lists[position])
    }

    override fun getItemCount() = lists.size

    override fun getItemViewType(position: Int): Int {
        return position
    }
}