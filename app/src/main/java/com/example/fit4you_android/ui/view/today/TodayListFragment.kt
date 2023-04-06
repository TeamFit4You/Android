package com.example.fit4you_android.ui.view.today

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fit4you_android.R

class TodayListFragment : Fragment() {

    companion object {
        fun newInstance() = TodayListFragment()
    }

    private lateinit var viewModel: TodayListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_today_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TodayListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}