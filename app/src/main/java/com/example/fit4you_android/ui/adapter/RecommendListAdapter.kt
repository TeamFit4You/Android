package com.example.fit4you_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fit4you_android.data.model.RecommendData
import com.example.fit4you_android.databinding.ItemRecommendListBinding
import com.example.fit4you_android.ui.adapter.ClickListener.OnRecomItemClickListener
import com.example.fit4you_android.ui.adapter.holder.RecommendListViewHolder
import com.example.fit4you_android.ui.view.recommend.RecommendListFragment
import com.example.fit4you_android.ui.view.recommend.RecommendListViewModel

class RecommendListAdapter(
    private val context: RecommendListFragment,
    private val recommendListViewModel: RecommendListViewModel,
    private val lists: List<RecommendData>,
    private val listener: OnRecomItemClickListener
): RecyclerView.Adapter<RecommendListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val listItemBinding = ItemRecommendListBinding.inflate(inflater, parent, false)
        return RecommendListViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: RecommendListViewHolder, position: Int) {
        holder.binding.listPart.text = lists[position].bodyPart
        holder.itemView.setOnClickListener {
            listener.onRecomItemClick(lists[position])
        }
        holder.bind(lists[position])
    }

    override fun getItemCount() = lists.size

    override fun getItemViewType(position: Int): Int {
        return position
    }
}