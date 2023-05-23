package com.example.fit4you_android.ui.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.fit4you_android.data.model.FitResult
import com.example.fit4you_android.databinding.ItemTodayFitResultBinding

class ResultListViewHolder(val binding: ItemTodayFitResultBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: FitResult) {
        binding.result = item
    }
}