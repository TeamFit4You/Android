package com.example.fit4you_android.ui.view.basicstatuscheck.posetest.video_NotUsed

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fit4you_android.R
import com.example.fit4you_android.ui.base.BaseViewModel

class VideoViewModel : BaseViewModel() {

    private val _videoUri = MutableLiveData<Uri>()
    val videoUri: LiveData<Uri>
        get() = _videoUri

    private val _fileN = MutableLiveData<Int>()
    val fileN: LiveData<Int>
        get() = _fileN

    private val sample_video = arrayListOf(
        R.raw.rom_ex_video,
        R.raw.rom_ex_video2,
        R.raw.rom_ex_video3,
        R.raw.rom_ex_video4,
        R.raw.rom_ex_video5
    )

    init {
        initDataSet()
    }

    private fun initDataSet() {
        _fileN.value = 0
    }

    fun setVideo(videoURI: String, num: Int) {
        _videoUri.value = Uri.parse(videoURI + sample_video[num])
        Log.d("FileIdx - VM, num", "${_fileN.value}, ${num}")
    }

    fun changeIdx() {
        Log.d("FileIdx - prev - VM", "${_fileN.value}")
        _fileN.value = _fileN.value?.plus(1)
        Log.d("FileIdx - after - VM", "${_fileN.value}")
    }
}