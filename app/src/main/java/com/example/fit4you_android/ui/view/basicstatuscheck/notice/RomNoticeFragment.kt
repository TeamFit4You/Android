package com.example.fit4you_android.ui.view.basicstatuscheck.notice

import androidx.fragment.app.viewModels
import com.example.fit4you_android.R
import com.example.fit4you_android.databinding.FragmentRomNoticeBinding
import com.example.fit4you_android.ui.base.BaseFragment

class RomNoticeFragment : BaseFragment<FragmentRomNoticeBinding, RomNoticeViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_rom_notice
    override val viewModel: RomNoticeViewModel by viewModels()

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun initView() {

    }
}