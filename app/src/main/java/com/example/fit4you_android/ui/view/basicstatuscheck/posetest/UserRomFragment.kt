package com.example.fit4you_android.ui.view.basicstatuscheck.posetest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.fit4you_android.R
import com.example.fit4you_android.databinding.FragmentUserRomBinding
import com.example.fit4you_android.ui.base.BaseFragment

class UserRomFragment : BaseFragment<FragmentUserRomBinding, UserRomViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_user_rom
    override val viewModel: UserRomViewModel by viewModels()

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun initView() {

    }
}