package com.example.fit4you_android.ui.view.recommend.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fit4you_android.data.Resource
import com.example.fit4you_android.data.repository.users.UserRepository
import com.example.fit4you_android.ui.base.BaseViewModel
import com.example.fit4you_android.util.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class RecomExpertViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {
    private val _expertVideo = MutableLiveData<Resource<ResponseBody>>()
    val expertVideo: LiveData<Resource<ResponseBody>>
        get() = _expertVideo

    private val _showToast = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>>
        get() = _showToast

    init {
        initDataSet()
    }

    private fun initDataSet() {

    }

    fun getExpertVideo(token: String, exerciseId: Long) {
        viewModelScope.launch {
            _expertVideo.value = Resource.Loading()
            userRepository.getExpertVideo(token, exerciseId).collect {
                _expertVideo.value = it
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