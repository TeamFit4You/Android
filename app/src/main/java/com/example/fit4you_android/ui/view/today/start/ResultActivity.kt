package com.example.fit4you_android.ui.view.today.start

import androidx.activity.viewModels
import com.example.fit4you_android.R
import com.example.fit4you_android.data.model.FitResult
import com.example.fit4you_android.databinding.ActivityResultBinding
import com.example.fit4you_android.ui.adapter.ResultListAdapter
import com.example.fit4you_android.ui.base.BaseActivity
import com.example.fit4you_android.util.MarginItemDecoration
import kotlin.math.roundToInt

class ResultActivity : BaseActivity<ActivityResultBinding, ResultViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_result
    override val viewModel: ResultViewModel by viewModels()
    private lateinit var adapter: ResultListAdapter

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun initView() {
        val datatemp = arrayListOf(
            FitResult(
                resources.getString(R.string.result_setN),
                resources.getString(R.string.result_feedBack)
            ),
            FitResult(
                resources.getString(R.string.result_setN),
                resources.getString(R.string.result_feedBack)
            ),
            FitResult(
                resources.getString(R.string.result_setN),
                resources.getString(R.string.result_feedBack)
            )
        )
        bindRVTodayListData(lists = datatemp)
    }

    private fun bindRVTodayListData(lists: ArrayList<FitResult>) {
        val spaceDecoration = MarginItemDecoration(
            resources.getDimension(R.dimen.today_bottom_space).roundToInt()
        )
        adapter = ResultListAdapter(this, viewModel, lists)
        binding.rvResult.adapter = adapter
        binding.rvResult.addItemDecoration(spaceDecoration)
    }
}