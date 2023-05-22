package com.example.fit4you_android.ui.view.recommend

import android.content.Intent
import androidx.fragment.app.viewModels
import com.example.fit4you_android.R
import com.example.fit4you_android.data.model.RecommendData
import com.example.fit4you_android.databinding.FragmentRecommendListBinding
import com.example.fit4you_android.ui.adapter.ClickListener.OnRecomItemClickListener
import com.example.fit4you_android.ui.adapter.RecommendListAdapter
import com.example.fit4you_android.ui.base.BaseFragment
import com.example.fit4you_android.ui.view.recommend.info.RecomInfoActivity
import com.example.fit4you_android.ui.view.today.start.ExampleActivity
import com.example.fit4you_android.util.MarginItemDecoration
import kotlin.math.roundToInt

class RecommendListFragment : BaseFragment<FragmentRecommendListBinding, RecommendListViewModel>(),
    OnRecomItemClickListener {
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
            RecommendData(resources.getString(R.string.today_list_ex1)),
            RecommendData(resources.getString(R.string.today_list_ex2)),
            RecommendData(resources.getString(R.string.today_list_ex3)),
            RecommendData(resources.getString(R.string.today_list_ex4)),
            RecommendData(resources.getString(R.string.today_list_ex3)),
            RecommendData(resources.getString(R.string.today_list_ex3)),
            RecommendData(resources.getString(R.string.today_list_ex4))
        )
        bindRVTodayListData(lists = datatemp)
    }

    override fun onRecomItemClick(item: RecommendData) {
        val intent = Intent(requireActivity(), RecomInfoActivity::class.java)
        intent.putExtra("key", item.bodyPart)
        startActivity(intent)
    }

    private fun bindRVTodayListData(lists: ArrayList<RecommendData>) {
        val spaceDecoration = MarginItemDecoration(
            resources.getDimension(R.dimen.today_bottom_space).roundToInt()
        )
        adapter = RecommendListAdapter(this, viewModel, lists, this)
        binding.rvRecomList.adapter = adapter
        binding.rvRecomList.addItemDecoration(spaceDecoration)
    }
}