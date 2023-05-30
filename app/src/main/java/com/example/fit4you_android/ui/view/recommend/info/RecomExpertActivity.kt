package com.example.fit4you_android.ui.view.recommend.info

import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.SeekBar
import android.widget.VideoView
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import com.example.fit4you_android.Fit4YouApp
import com.example.fit4you_android.R
import com.example.fit4you_android.data.Resource
import com.example.fit4you_android.databinding.ActivityRecomExpertBinding
import com.example.fit4you_android.ui.base.BaseActivity
import com.example.fit4you_android.util.*
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.ResponseBody
import java.io.File
import java.io.InputStream

@AndroidEntryPoint
class RecomExpertActivity : BaseActivity<ActivityRecomExpertBinding, RecomExpertViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_recom_expert
    override val viewModel: RecomExpertViewModel by viewModels()

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {
        observeViewModel()
    }

    override fun initView() {
        val token = Fit4YouApp.prefs.getString("accessToken", "")
        val bodyPart = intent.getStringExtra("key")
        val exerciseId = intent.getLongExtra("exerciseId", 0)
        val detail = intent.getStringExtra("detail")
        val newDetail = detail?.replace("\\n", "\n")
        Log.d("workoutId", "$exerciseId")

        binding.tbExpertInfo.text = bodyPart
        binding.tvNotice.text = newDetail
        viewModel.getExpertVideo(token, exerciseId)
    }

    private fun observeViewModel() {
        observe(viewModel.expertVideo, ::handleVideoResult)
        observeToast(viewModel.showToast)
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }

    private fun handleVideoResult(status: Resource<ResponseBody>){
        when(status){
            is Resource.Loading -> {
                binding.lottieExpertVideo.toVisible()
                binding.lottieExpertVideo.playAnimation()
            }
            is Resource.Success -> {
                binding.lottieExpertVideo.pauseAnimation()
                binding.lottieExpertVideo.toGone()
                val videoUrl = status.data
                val inputStream = videoUrl.byteStream()
                val videoFile = createVideoFile()
                inputStream.saveToFile(videoFile)
                val videoPath = videoFile.absolutePath

                binding.vvExpertEx.setVideoPath(videoPath)
                setVideoButton(binding.vvExpertEx)
                setSeekBar(binding.vvExpertEx, binding.seekBarExpert)
            }
            is Resource.Error -> {

            }
        }
    }

    fun InputStream.saveToFile(file: File) {
        use { input ->
            file.outputStream().use { output ->
                input.copyTo(output)
            }
        }
    }

    private fun createVideoFile(): File {
        val fileName = "myVideo.mp4"
        val storageDir = this.getExternalFilesDir(Environment.DIRECTORY_MOVIES)
        return File(storageDir, fileName)
    }

    private fun setVideoButton(video: VideoView) {
        binding.btnExpertPlay.setOnClickListener {
            video.start()
            binding.btnExpertPlay.isEnabled = false
            binding.btnExpertPause.isEnabled = true
        }
        binding.btnExpertPause.setOnClickListener {
            video.pause()
            binding.btnExpertPlay.isEnabled = true
            binding.btnExpertPause.isEnabled = false
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