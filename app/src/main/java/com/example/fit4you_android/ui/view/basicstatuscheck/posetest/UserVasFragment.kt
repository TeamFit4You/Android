package com.example.fit4you_android.ui.view.basicstatuscheck.posetest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.fit4you_android.R
import com.example.fit4you_android.databinding.FragmentUserVasBinding
import com.example.fit4you_android.ui.base.BaseFragment

class UserVasFragment : BaseFragment<FragmentUserVasBinding, UserVasViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_user_vas
    override val viewModel: UserVasViewModel by viewModels()

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun initView() {

    }
}