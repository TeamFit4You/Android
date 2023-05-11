package com.example.fit4you_android.ui.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.fit4you_android.data.RecommendData
import com.example.fit4you_android.databinding.ItemRecommendListBinding

class RecommendListViewHolder(val binding: ItemRecommendListBinding) : RecyclerView.ViewHolder(binding.root)  {
    fun bind(item: RecommendData){
        binding.recom = item
    }
}