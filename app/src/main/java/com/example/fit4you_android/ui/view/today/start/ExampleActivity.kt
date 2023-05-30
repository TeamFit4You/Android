package com.example.fit4you_android.ui.view.today.start

import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.SeekBar
import android.widget.VideoView
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import com.example.fit4you_android.Fit4YouApp
import com.example.fit4you_android.R
import com.example.fit4you_android.data.Resource
import com.example.fit4you_android.databinding.ActivityExampleBinding
import com.example.fit4you_android.ui.base.BaseActivity
import com.example.fit4you_android.util.*
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.ResponseBody
import java.io.File
import java.io.InputStream

@AndroidEntryPoint
class ExampleActivity : BaseActivity<ActivityExampleBinding, ExampleViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_example
    override val viewModel: ExampleViewModel by viewModels()

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {
        observeViewModel()
    }

    override fun initView() {
        val token = Fit4YouApp.prefs.getString("accessToken", "")
        val bodyPart = intent.getStringExtra("key")
        val workoutId = intent.getLongExtra("workoutId", 0)
        val detail = intent.getStringExtra("detail")
        val newDetail = detail?.replace("\\n","\n")
        Log.d("workoutId", "$workoutId")

        binding.tbExamPart.text = bodyPart
        binding.tvNotice.text = newDetail
        viewModel.getTodayVideo(token, workoutId)

        binding.btnExamNext.setOnClickListener {
            val intent = Intent(this, SelfActivity::class.java)
            intent.putExtra("tool", bodyPart)
            startActivity(intent)
        }
    }

    private fun observeViewModel(){
        observe(viewModel.todayVideo, ::handleVideoResult)
        observeToast(viewModel.showToast)
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }

    private fun handleVideoResult(status: Resource<ResponseBody>){
        when(status){
            is Resource.Loading -> {
                binding.lottieTodayVideo.toVisible()
                binding.lottieTodayVideo.playAnimation()
            }
            is Resource.Success -> {
                binding.lottieTodayVideo.pauseAnimation()
                binding.lottieTodayVideo.toGone()
                val videoUrl = status.data
                val inputStream = videoUrl.byteStream()
                val videoFile = createVideoFile()
                inputStream.saveToFile(videoFile)
                val videoPath = videoFile.absolutePath

                binding.vvTodayEx.setVideoPath(videoPath)
                setVideoButton(binding.vvTodayEx)
                setSeekBar(binding.vvTodayEx, binding.seekBarToday)
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