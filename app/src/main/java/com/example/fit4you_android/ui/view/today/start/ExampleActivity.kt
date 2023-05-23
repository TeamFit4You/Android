package com.example.fit4you_android.ui.view.today.start

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.SeekBar
import android.widget.VideoView
import androidx.activity.viewModels
import com.example.fit4you_android.R
import com.example.fit4you_android.databinding.ActivityExampleBinding
import com.example.fit4you_android.ui.base.BaseActivity
import com.example.fit4you_android.ui.view.today.TodayActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExampleActivity : BaseActivity<ActivityExampleBinding, ExampleViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_example
    override val viewModel: ExampleViewModel by viewModels()

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun initView() {
        val bodyPart = intent.getStringExtra("key")
        binding.tbExamPart.text = bodyPart

        // 오늘의 운동 아이템 클릭시 해당 운동에 대한 영상 불러오기 구현

        // -----------------------------------------------

        setVideoButton(binding.vvTodayEx)
        setSeekBar(binding.vvTodayEx, binding.seekBarToday)

//        binding.btnExamPrev.setOnClickListener {
//            val intent = Intent(this, TodayActivity::class.java)
//            startActivity(intent)
//            finish()
//        }

        binding.btnExamNext.setOnClickListener {
            val intent = Intent(this, SelfActivity::class.java)
            intent.putExtra("tool", bodyPart)
            startActivity(intent)
        }
    }

    private fun setVideoButton(video: VideoView) {
        binding.btnTodayPlay.setOnClickListener {
            video.start()
            binding.btnTodayPlay.isEnabled = false
            binding.btnTodayPause.isEnabled = true
        }
        binding.btnTodayPause.setOnClickListener {
            video.pause()
            binding.btnTodayPlay.isEnabled = true
            binding.btnTodayPause.isEnabled = false
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