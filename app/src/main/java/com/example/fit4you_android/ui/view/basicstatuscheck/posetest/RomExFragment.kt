package com.example.fit4you_android.ui.view.basicstatuscheck.posetest

import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.SeekBar
import android.widget.VideoView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.fit4you_android.MainActivity
import com.example.fit4you_android.R
import com.example.fit4you_android.databinding.FragmentRomExBinding
import com.example.fit4you_android.ui.base.BaseFragment

class RomExFragment : BaseFragment<FragmentRomExBinding, RomExViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_rom_ex
    override val viewModel: RomExViewModel by viewModels()

    override fun initBeforeBinding() {
        binding.lifecycleOwner = this
    }

    override fun initAfterBinding() {
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.fileN.observe(viewLifecycleOwner, Observer {
            Log.d("VideoFrag", "$it")
            val baseVideoUri =
                Uri.parse("android.resource://" + requireActivity().packageName + "/")
            viewModel.setVideo(baseVideoUri.toString(), it.toInt())
            binding.vvRomEx.setVideoURI(viewModel.videoUri.value)
            setVideoButton(binding.vvRomEx)
            setSeekBar(binding.vvRomEx, binding.seekBar)
        })
    }

    override fun initView() {
        val baseVideoUri =
            Uri.parse("android.resource://" + requireActivity().packageName + "/")
        viewModel.setVideo(baseVideoUri.toString(), viewModel.fileN.value!!)
        binding.vvRomEx.setVideoURI(viewModel.videoUri.value)
        setVideoButton(binding.vvRomEx)
        setSeekBar(binding.vvRomEx, binding.seekBar)

//        binding.btnFragRomNext.setOnClickListener {
//            if (viewModel.fileN.value == viewModel.size.value?.minus(1)) {
//                val intent = Intent(requireActivity(), MainActivity::class.java)
//                startActivity(intent)
//            } else {
//                viewModel.changeIdx()
//            }
//        }
    }

    private fun setVideoButton(video: VideoView) {
        binding.btnPlay.setOnClickListener {
            video.start()
            binding.btnPlay.isEnabled = false
            binding.btnPause.isEnabled = true
        }
        binding.btnPause.setOnClickListener {
            video.pause()
            binding.btnPlay.isEnabled = true
            binding.btnPause.isEnabled = false
        }
    }

    private fun setSeekBar(video: VideoView, seekBar: SeekBar) {
        video.setOnPreparedListener {
            seekBar.max = video.duration

            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed(object : Runnable {
                override fun run() {
                    seekBar.progress = video.currentPosition
                    handler.postDelayed(this, 1000)
                }
            }, 0)
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    video.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}