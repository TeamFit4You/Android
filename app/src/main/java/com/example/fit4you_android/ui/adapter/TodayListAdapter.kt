package com.example.fit4you_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fit4you_android.data.model.TodayList
import com.example.fit4you_android.databinding.ItemTodayListBinding
import com.example.fit4you_android.ui.adapter.holder.TodayListViewHolder
import com.example.fit4you_android.ui.view.today.TodayListFragment
import com.example.fit4you_android.ui.view.today.TodayListViewModel

class TodayListAdapter(
    private val context: TodayListFragment,
    private val todayListViewModel: TodayListViewModel,
    private val lists: List<TodayList>
): RecyclerView.Adapter<TodayListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodayListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val listItemBinding = ItemTodayListBinding.inflate(inflater, parent, false)
        return TodayListViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: TodayListViewHolder, position: Int) {
        holder.binding.listPart.text = lists[position].bodyPart
        holder.bind(lists[position])
    }

    override fun getItemCount() = lists.size

    override fun getItemViewType(position: Int): Int {
        return position
    }
}