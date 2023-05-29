package com.example.fit4you_android.ui.view.today

import android.content.Intent
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import com.example.fit4you_android.R
import com.example.fit4you_android.data.Resource
import com.example.fit4you_android.data.dto.response.StringListRes
import com.example.fit4you_android.data.dto.response.TodayListRes
import com.example.fit4you_android.data.model.TodayList
import com.example.fit4you_android.databinding.FragmentTodayListBinding
import com.example.fit4you_android.ui.adapter.ClickListener.OnTodayItemClickListener
import com.example.fit4you_android.ui.adapter.TodayListAdapter
import com.example.fit4you_android.ui.base.BaseFragment
import com.example.fit4you_android.ui.view.today.start.ExampleActivity
import com.example.fit4you_android.util.*
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class TodayListFragment : BaseFragment<FragmentTodayListBinding, TodayListViewModel>(),
    OnTodayItemClickListener {
    override val viewModel: TodayListViewModel by viewModels()
    override val layoutResourceId: Int
        get() = R.layout.fragment_today_list
    private lateinit var adapter: TodayListAdapter

    override fun initBeforeBinding() {
        binding.lifecycleOwner = this
    }

    override fun initAfterBinding() {
        observeViewModel()
    }

    override fun initView() {
        val email = arguments?.getString("email")
        if (email != null) {
            viewModel.getTodayList(email = email)
        }
    }

    private fun observeViewModel() {
        observe(viewModel.todayList, ::handleTodayResult)
        observeToast(viewModel.showToast)
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }

    private fun handleTodayResult(status: Resource<TodayListRes>) {
        Log.d("ResponseData", "$status")
        when (status) {
            is Resource.Loading -> {
                binding.lottieToday.toVisible()
                binding.lottieToday.playAnimation()
            }
            is Resource.Success -> status.data.let {
                binding.lottieToday.pauseAnimation()
                binding.lottieToday.toGone()
                val list = status.data.workouts
                Log.d("TodayListFrag_LongList","$list")
                list.forEach { item ->
                    viewModel.getTodayString(item) // id를 통해 각 항목의 상세 데이터를 불러옴
                    observe(viewModel.stringList, ::handleStringList)
                }
//                bindRVTodayListData(status.data.workouts)
            }
            is Resource.Error -> {
                Log.d("handleMenuResult", "실패!")
                binding.lottieToday.pauseAnimation()
                binding.lottieToday.toGone()
                viewModel.showToastMessage(status.message)
            }
        }
    }

    private fun handleStringList(status: Resource<StringListRes>) {
        Log.d("String_Res", "$status")
        when(status){
            is Resource.Loading -> {
                binding.lottieToday.toVisible()
                binding.lottieToday.playAnimation()
            }
            is Resource.Success-> status.data.let {
                binding.lottieToday.pauseAnimation()
                binding.lottieToday.toGone()

                // keypoint
                val todayList = it.part.map { string ->
                    TodayList(string.toString()) // 여기서 TodayList는 적절한 생성자를 제공해야 합니다.
                }
                Log.d("TodayListFrag_StringList","$todayList")

                bindRVTodayListData(todayList)
            }
            is Resource.Error -> {
                Log.d("handleMenuResult", "실패!")
                binding.lottieToday.pauseAnimation()
                binding.lottieToday.toGone()
                viewModel.showToastMessage(status.message)
            }
        }
    }

    override fun onTodayItemClick(item: TodayList) {
        val intent = Intent(requireActivity(), ExampleActivity::class.java)
        intent.putExtra("key", item.bodyPart)
        startActivity(intent)
    }

    private fun bindRVTodayListData(lists: List<TodayList>) {
        val spaceDecoration = MarginItemDecoration(
            resources.getDimension(R.dimen.today_bottom_space).roundToInt()
        )
        adapter = TodayListAdapter(this, viewModel, lists, this)
        binding.rvTodayList.adapter = adapter
        binding.rvTodayList.addItemDecoration(spaceDecoration)
    }
}