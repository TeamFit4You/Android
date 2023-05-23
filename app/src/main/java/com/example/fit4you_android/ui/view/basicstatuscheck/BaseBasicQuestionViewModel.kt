package com.example.fit4you_android.ui.view.basicstatuscheck

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fit4you_android.R
import com.example.fit4you_android.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

class BaseBasicQuestionViewModel : BaseViewModel() {
    private val _videoUri = MutableLiveData<Uri>()
    val videoUri: LiveData<Uri>
        get() = _videoUri

    private val _fileN = MutableLiveData<Int>()
    val fileN: LiveData<Int>
        get() = _fileN

    init {
        initDataSet()
    }

    private fun initDataSet() {
        _fileN.value = 0
    }

    fun setVideo(videoUri: String, num: Int) {
        _videoUri.value = Uri.parse(videoUri + num)
    }

    fun changeIdx() {
        _fileN.value = _fileN.value?.plus(1)
    }
}