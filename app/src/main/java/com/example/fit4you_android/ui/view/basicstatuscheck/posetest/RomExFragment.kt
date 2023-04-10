package com.example.fit4you_android.ui.view.basicstatuscheck.posetest

import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.widget.SeekBar
import androidx.fragment.app.viewModels
import com.example.fit4you_android.R
import com.example.fit4you_android.databinding.FragmentRomExBinding
import com.example.fit4you_android.ui.base.BaseFragment

class RomExFragment : BaseFragment<FragmentRomExBinding, RomExViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_rom_ex
    override val viewModel: RomExViewModel by viewModels()

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun initView() {
        val sample_video = arrayListOf(
            R.raw.rom_ex_video,
            R.raw.rom_ex_video2,
            R.raw.rom_ex_video3,
            R.raw.rom_ex_video4,
            R.raw.rom_ex_video5
        )
        val video = binding.vvRomEx
        val seekBar = binding.seekBar

        val videoUri =
            Uri.parse("android.resource://" + requireActivity().packageName + "/" + sample_video[1])
        video.setVideoURI(videoUri)

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