package com.example.fit4you_android.ui.view.today

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fit4you_android.data.Resource
import com.example.fit4you_android.data.dto.response.StringListRes
import com.example.fit4you_android.data.dto.response.TodayListRes
import com.example.fit4you_android.data.repository.users.UserRepository
import com.example.fit4you_android.ui.base.BaseViewModel
import com.example.fit4you_android.util.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodayListViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {
    private val _todayList = MutableLiveData<Resource<TodayListRes>>()
    val todayList: LiveData<Resource<TodayListRes>>
        get() = _todayList

    private val _stringList = MutableLiveData<Resource<StringListRes>>()
    val stringList: LiveData<Resource<StringListRes>>
        get() = _stringList

    private val _showToast = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>>
        get() = _showToast

    init {
        initDataset()
    }

    private fun initDataset() {

    }

    fun getTodayList(email: String) {
        viewModelScope.launch {
            _todayList.value = Resource.Loading()
            userRepository.getTodayList(query = email).collect {
                _todayList.value = it
            }
        }
    }

    fun getTodayString(workoutId: Long){
        viewModelScope.launch {
            _stringList.value = Resource.Loading()
            userRepository.getTodayString(workoutId).collect{
                _stringList.value = it
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