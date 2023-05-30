package com.example.fit4you_android.ui.view.today.start

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fit4you_android.data.Resource
import com.example.fit4you_android.data.repository.users.UserRepository
import com.example.fit4you_android.ui.base.BaseViewModel
import com.example.fit4you_android.util.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class ExampleViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {
    private val _videoUri = MutableLiveData<Uri>()
    val videoUri: LiveData<Uri>
        get() = _videoUri

    private val _todayVideo = MutableLiveData<Resource<ResponseBody>>()
    val todayVideo: LiveData<Resource<ResponseBody>>
        get() = _todayVideo

    private val _showToast = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>>
        get() = _showToast

    init {
        initDataSet()
    }

    private fun initDataSet() {

    }

    fun getTodayVideo(token: String, workoutId: Long) {
        viewModelScope.launch {
            _todayVideo.value = Resource.Loading()
            userRepository.getTodayVideo(token, workoutId).collect {
                _todayVideo.value = it
            }
        }
    }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        _showToast.value = SingleEvent(error.description)
    }

    fun showToastMessage(errorMessage: String?) {
        if (errorMessage == null) return
        _showToast.value = SingleEvent(errorMessage)
    }
}