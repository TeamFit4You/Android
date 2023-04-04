package com.example.fit4you_android.ui.view.basicstatuscheck.posetest

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fit4you_android.R

class PoseTestFragment : Fragment() {

    companion object {
        fun newInstance() = PoseTestFragment()
    }

    private lateinit var viewModel: PoseTestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pose_test, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PoseTestViewModel::class.java)
        // TODO: Use the ViewModel
    }

}