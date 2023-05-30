package com.example.fit4you_android.ui.view.today

import android.content.Intent
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import com.example.fit4you_android.Fit4YouApp
import com.example.fit4you_android.R
import com.example.fit4you_android.data.Resource
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
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@AndroidEntryPoint
class TodayListFragment : BaseFragment<FragmentTodayListBinding, TodayListViewModel>(),
    OnTodayItemClickListener {
    override val viewModel: TodayListViewModel by viewModels()
    override val layoutResourceId: Int
        get() = R.layout.fragment_today_list
    private lateinit var adapter: TodayListAdapter

    private lateinit var lists: List<Long>

    override fun initBeforeBinding() {
        binding.lifecycleOwner = this
    }

    override fun initAfterBinding() {
        observeViewModel()
    }

    override fun initView() {
        val email = arguments?.getString("email")
        val token = Fit4YouApp.prefs.getString("accessToken", "")
        if (email != null) {
            viewModel.getTodayList(token, email)
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
        val token = Fit4YouApp.prefs.getString("accessToken", "")
        when (status) {
            is Resource.Loading -> {
                binding.lottieToday.toVisible()
                binding.lottieToday.playAnimation()
            }
            is Resource.Success -> status.data.let {
                binding.lottieToday.pauseAnimation()
                binding.lottieToday.toGone()
                lists = status.data.workoutIds
                Log.d("TodayListFrag_LongList", "$lists")

                lifecycleScope.launch {
                    val mappedList = lists.map { id ->
                        when (val response = viewModel.getTodayString(token, id)) {
                            is Resource.Loading -> null
                            is Resource.Success -> {
                                // handle success
                                TodayList(id, response.data.diseaseName, response.data.detail)
                            }
                            is Resource.Error -> {
                                // handle error
                                Log.d("handleMenuResult", "실패!")
                                viewModel.showToastMessage(response.message)
                                null
                            }
                        }
                    }.filterNotNull()

                    bindRVTodayListData(mappedList)
                }
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
        intent.putExtra("workoutId", item.id)
        intent.putExtra("detail", item.detail)
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