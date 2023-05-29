package com.example.fit4you_android.ui.view.recommend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fit4you_android.data.Resource
import com.example.fit4you_android.data.dto.request.RecomListReq
import com.example.fit4you_android.data.dto.response.RecomListRes
import com.example.fit4you_android.data.repository.users.UserRepository
import com.example.fit4you_android.ui.base.BaseViewModel
import com.example.fit4you_android.util.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendListViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {

    private val _recomList = MutableLiveData<Resource<List<RecomListRes>>>()
    val recomList: LiveData<Resource<List<RecomListRes>>>
        get() = _recomList

    private val _showToast = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>>
        get() = _showToast

    init {
        initDataset()
    }

    private fun initDataset() {

    }

    fun getRecomList(list: RecomListReq) {
        viewModelScope.launch {
            _recomList.value = Resource.Loading()
            userRepository.getRecomList(list).collect{
                _recomList.value = it
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