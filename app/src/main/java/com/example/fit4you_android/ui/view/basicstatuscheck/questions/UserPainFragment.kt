package com.example.fit4you_android.ui.view.basicstatuscheck.questions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.fit4you_android.R
import com.example.fit4you_android.databinding.FragmentUserPainBinding
import com.example.fit4you_android.ui.base.BaseFragment
import com.example.fit4you_android.ui.view.basicstatuscheck.posetest.RomExFragment

class UserPainFragment : BaseFragment<FragmentUserPainBinding, UserPainViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_user_pain
    override val viewModel: UserPainViewModel by viewModels()

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun initView() {

    }
}