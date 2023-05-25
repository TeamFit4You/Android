package com.example.fit4you_android.ui.view.basicstatuscheck

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fit4you_android.data.Resource
import com.example.fit4you_android.data.dto.request.BaseQuestionReq
import com.example.fit4you_android.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch


class BaseBasicQuestionViewModel : BaseViewModel() {
    private val _videoUri = MutableLiveData<Uri>()
    val videoUri: LiveData<Uri>
        get() = _videoUri

//    private val _survey = MutableLiveData<BaseQuestionReq>()
//    val survey: LiveData<BaseQuestionReq>
//        get() = _survey

    val baseQuestionReq: MutableLiveData<BaseQuestionReq> = MutableLiveData(
        BaseQuestionReq(
            diag = emptyList(),
            vas1 = 0,
            vas2 = 0,
            vas3 = 0,
            vas4 = 0,
            vas5 = 0,
            vas6 = 0
        )
    )

    init {
        initDataSet()
    }

    private fun initDataSet() {

    }

    fun setVideo(videoUri: String, num: Int) {
        _videoUri.value = Uri.parse(videoUri + num)
    }

//    fun setValue(currentReq: BaseQuestionReq){
//        _survey.value = currentReq
//    }

    fun postQuestion(request: BaseQuestionReq) {

    }

    fun updateVas1(vas1: Int) {
        baseQuestionReq.value?.vas1 = vas1
//        _survey.value?.vas1=vas1
    }

    fun updateVas2(vas2: Int) {
        baseQuestionReq.value?.vas2 = vas2
//        _survey.value?.vas2 = vas2
    }

    fun updateVas3(vas3: Int) {
        baseQuestionReq.value?.vas3 = vas3
//        _survey.value?.vas3 = vas3
    }

    fun updateVas4(vas4: Int) {
        baseQuestionReq.value?.vas4 = vas4
//        _survey.value?.vas4 = vas4
    }

    fun updateVas5(vas5: Int) {
        baseQuestionReq.value?.vas5 = vas5
//        _survey.value?.vas5 = vas5
    }

    fun updateVas6(vas6: Int) {
        baseQuestionReq.value?.vas6 = vas6
//        _survey.value?.vas6 = vas6
    }
}