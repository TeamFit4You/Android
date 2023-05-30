package com.example.fit4you_android.ui.view.recommend

import android.content.Intent
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import com.example.fit4you_android.Fit4YouApp
import com.example.fit4you_android.R
import com.example.fit4you_android.data.Resource
import com.example.fit4you_android.data.dto.request.RecomListReq
import com.example.fit4you_android.data.dto.response.RecomListRes
import com.example.fit4you_android.data.dto.response.TodayListRes
import com.example.fit4you_android.data.model.RecommendData
import com.example.fit4you_android.data.model.TodayList
import com.example.fit4you_android.databinding.FragmentRecommendListBinding
import com.example.fit4you_android.ui.adapter.ClickListener.OnRecomItemClickListener
import com.example.fit4you_android.ui.adapter.RecommendListAdapter
import com.example.fit4you_android.ui.base.BaseFragment
import com.example.fit4you_android.ui.view.recommend.info.RecomInfoActivity
import com.example.fit4you_android.util.*
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@AndroidEntryPoint
class RecommendListFragment : BaseFragment<FragmentRecommendListBinding, RecommendListViewModel>(),
    OnRecomItemClickListener {
    override val layoutResourceId: Int
        get() = R.layout.fragment_recommend_list
    override val viewModel: RecommendListViewModel by viewModels()
    private lateinit var adapter: RecommendListAdapter

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {
        observeViewModel()
    }

    override fun initView() {
        val token = Fit4YouApp.prefs.getString("accessToken", "")
        val email = arguments?.getString("email")
        if (email != null) {
            val recomList = RecomListReq(
                email, "전체"
            )
            viewModel.getRecomList(token, recomList)
        }
    }

    private fun observeViewModel() {
        observe(viewModel.recomList, ::handleRecomResult)
        observeToast(viewModel.showToast)
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }

    private fun handleRecomResult(status: Resource<List<RecomListRes>>) {
        Log.d("ResponseData_recom", "$status")
        when (status) {
            is Resource.Loading -> {
                binding.lottieToday.toVisible()
                binding.lottieToday.playAnimation()
            }
            is Resource.Success -> status.data.let {
                binding.lottieToday.pauseAnimation()
                binding.lottieToday.toGone()

                val recomList = it.map { res -> RecommendData(res.exerciseId, res.diseaseName, res.detail) }
                bindRVTodayListData(recomList)
            }
            is Resource.Error -> {
                Log.d("handleMenuResult", "실패!")
                binding.lottieToday.pauseAnimation()
                binding.lottieToday.toGone()
                viewModel.showToastMessage(status.message)
            }
        }
    }

    override fun onRecomItemClick(item: RecommendData) {
        val intent = Intent(requireActivity(), RecomInfoActivity::class.java)
        intent.putExtra("key", item.bodyPart)
        intent.putExtra("exerciseId", item.id)
        intent.putExtra("detail", item.detail)
        startActivity(intent)
    }

    private fun bindRVTodayListData(lists: List<RecommendData>) {
        val spaceDecoration = MarginItemDecoration(
            resources.getDimension(R.dimen.today_bottom_space).roundToInt()
        )
        adapter = RecommendListAdapter(this, viewModel, lists, this)
        binding.rvRecomList.adapter = adapter
        binding.rvRecomList.addItemDecoration(spaceDecoration)
    }
}