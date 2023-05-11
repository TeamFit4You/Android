package com.example.fit4you_android.ui.view.basicstatuscheck.questions

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.fit4you_android.R
import com.example.fit4you_android.databinding.FragmentPhysicalBinding
import com.example.fit4you_android.ui.base.BaseFragment

class PhysicalFragment : BaseFragment<FragmentPhysicalBinding, PhysicalViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_physical
    override val viewModel: PhysicalViewModel by viewModels()

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun initView() {

    }
}