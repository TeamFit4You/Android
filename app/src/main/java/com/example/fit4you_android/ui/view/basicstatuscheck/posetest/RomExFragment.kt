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
//        val videoView = view.findViewById<VideoView>(R.id.video_view)
//        val videoUri = Uri.parse("android.resource://" + requireActivity().packageName + "/" + R.raw.video_file_name)
//        videoView.setVideoURI(videoUri)
//        videoView.start()
        val video = binding.vvRomEx
        val seekBar = binding.seekBar

        val videoUri =
            Uri.parse("android.resource://" + requireActivity().packageName + "/" + R.raw.rom_ex_video)
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