package com.example.fit4you_android.ui.view.recommend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.fit4you_android.R
import com.example.fit4you_android.data.local.RecommendData
import com.example.fit4you_android.data.local.TodayList
import com.example.fit4you_android.databinding.FragmentRecommendListBinding
import com.example.fit4you_android.ui.adapter.RecommendListAdapter
import com.example.fit4you_android.ui.adapter.TodayListAdapter
import com.example.fit4you_android.ui.base.BaseFragment
import com.example.fit4you_android.ui.view.today.TodayListViewModel
import com.example.fit4you_android.util.MarginItemDecoration
import kotlin.math.roundToInt

class RecommendListFragment : BaseFragment<FragmentRecommendListBinding, RecommendListViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_recommend_list
    override val viewModel: RecommendListViewModel by viewModels()
    private lateinit var adapter: RecommendListAdapter

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun initView() {
        val datatemp = arrayListOf(
            RecommendData(
                resources.getString(R.string.today_list_ex1),
                resources.getString(R.string.recom_spec1_1),
                resources.getString(R.string.recom_spec1_2),
                resources.getString(R.string.recom_spec1_3)
            ),
            RecommendData(
                resources.getString(R.string.today_list_ex2),
                resources.getString(R.string.recom_spec2_1),
                resources.getString(R.string.recom_spec2_2),
                resources.getString(R.string.recom_spec2_3)
            ),
            RecommendData(
                resources.getString(R.string.today_list_ex3),
                resources.getString(R.string.recom_spec1_1),
                resources.getString(R.string.recom_spec1_2),
                resources.getString(R.string.recom_spec1_3)
            ),
            RecommendData(
                resources.getString(R.string.today_list_ex4),
                resources.getString(R.string.recom_spec2_1),
                resources.getString(R.string.recom_spec2_2),
                resources.getString(R.string.recom_spec2_3)
            ),
            RecommendData(
                resources.getString(R.string.today_list_ex3),
                resources.getString(R.string.recom_spec1_1),
                resources.getString(R.string.recom_spec1_2),
                resources.getString(R.string.recom_spec1_3)
            ),
            RecommendData(
                resources.getString(R.string.today_list_ex3),
                resources.getString(R.string.recom_spec1_1),
                resources.getString(R.string.recom_spec1_2),
                resources.getString(R.string.recom_spec1_3)
            ),
            RecommendData(
                resources.getString(R.string.today_list_ex4),
                resources.getString(R.string.recom_spec2_1),
                resources.getString(R.string.recom_spec2_2),
                resources.getString(R.string.recom_spec2_3)
            )
        )
        bindRVTodayListData(lists = datatemp)
    }

    private fun bindRVTodayListData(lists: ArrayList<RecommendData>) {
        val spaceDecoration = MarginItemDecoration(
            resources.getDimension(R.dimen.today_bottom_space).roundToInt()
        )
        adapter = RecommendListAdapter(this, viewModel, lists)
        binding.rvRecomList.adapter = adapter
        binding.rvRecomList.addItemDecoration(spaceDecoration)
    }
}