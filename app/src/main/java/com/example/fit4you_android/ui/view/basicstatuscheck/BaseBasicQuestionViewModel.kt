package com.example.fit4you_android.ui.view.basicstatuscheck

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fit4you_android.data.Resource
import com.example.fit4you_android.data.dto.request.BaseQuestionReq
import com.example.fit4you_android.data.dto.response.BaseQuestionRes
import com.example.fit4you_android.data.repository.users.UserRepository
import com.example.fit4you_android.ui.base.BaseViewModel
import com.example.fit4you_android.util.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BaseBasicQuestionViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {
    private val _videoUri = MutableLiveData<Uri>()
    val videoUri: LiveData<Uri>
        get() = _videoUri

    private val _showToast = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>>
        get() = _showToast

    private val _survey = MutableLiveData<Resource<Unit>>()
    val survey: LiveData<Resource<Unit>>
        get() = _survey

    val baseQuestionReq: MutableLiveData<BaseQuestionReq> = MutableLiveData(
        BaseQuestionReq(
            email="",
            neck = 0F,
            shoulder = 0F,
            lumbar = 0F,
            wrist = 0F,
            elbow = 0F,
            knee = 0F,
            hist = emptyList()
        )
    )

    init {
        initDataSet()
    }

    private fun initDataSet() {

    }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        _showToast.value = SingleEvent(error.description)
    }

    fun showToastMessage(errorMessage: String?) {
        if (errorMessage == null) return
        _showToast.value = SingleEvent(errorMessage)
    }

    fun setVideo(videoUri: String, num: Int) {
        _videoUri.value = Uri.parse(videoUri + num)
    }

    fun setEmail(email: String) {
        baseQuestionReq.value?.email = email
    }

    fun postQuestion(email: String,hist:List<String>,vas1: Float,vas2: Float,vas3: Float,vas4: Float,vas5: Float,vas6: Float) {
        viewModelScope.launch {
            _survey.value = Resource.Loading()
            userRepository.postSurvey(BaseQuestionReq(email,vas1,vas2,vas3,vas4,vas5,vas6,hist)).collect {
                _survey.value = it
            }
        }
    }

    fun updateVas1(vas1: Float) {
        baseQuestionReq.value?.neck = vas1
    }

    fun updateVas2(vas2: Float) {
        baseQuestionReq.value?.shoulder = vas2
    }

    fun updateVas3(vas3: Float) {
        baseQuestionReq.value?.lumbar = vas3
    }

    fun updateVas4(vas4: Float) {
        baseQuestionReq.value?.wrist = vas4
    }

    fun updateVas5(vas5: Float) {
        baseQuestionReq.value?.elbow = vas5
    }

    fun updateVas6(vas6: Float) {
        baseQuestionReq.value?.knee = vas6
    }
}