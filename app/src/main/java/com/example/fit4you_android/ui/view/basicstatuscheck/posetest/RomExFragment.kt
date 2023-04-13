package com.example.fit4you_android.ui.view.basicstatuscheck.posetest

import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.SeekBar
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.fit4you_android.R
import com.example.fit4you_android.databinding.FragmentRomExBinding
import com.example.fit4you_android.ui.base.BaseFragment
import com.example.fit4you_android.ui.view.basicstatuscheck.posetest.video.VideoFragment
import com.example.fit4you_android.ui.view.basicstatuscheck.posetest.video.VideoViewModel

class RomExFragment : BaseFragment<FragmentRomExBinding, VideoViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_rom_ex
    override val viewModel: VideoViewModel by viewModels()

    override fun initBeforeBinding() {
        binding.lifecycleOwner = this
    }

    override fun initAfterBinding() {
        observeViewModel()
    }

    override fun initView() {
        childFragmentManager
            .beginTransaction()
            .replace(R.id.rom_ex_frag, VideoFragment())
            .commit()

        binding.btnFragRomNext.setOnClickListener {
            viewModel.changeIdx()
            childFragmentManager
                .beginTransaction()
                .replace(R.id.rom_ex_frag, VideoFragment())
                .commit()
        }
    }

    private fun observeViewModel() {
        viewModel.fileN.observe(viewLifecycleOwner, Observer { value ->

        })
    }
}