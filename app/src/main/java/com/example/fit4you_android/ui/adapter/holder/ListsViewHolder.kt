package com.example.fit4you_android.ui.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.fit4you_android.data.local.TodayList
import com.example.fit4you_android.databinding.ItemTodayListBinding

class ListsViewHolder(val binding: ItemTodayListBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: TodayList){
        binding.bodypart = item
    }
}