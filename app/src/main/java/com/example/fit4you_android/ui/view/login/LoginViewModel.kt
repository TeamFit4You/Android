package com.example.fit4you_android.ui.view.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fit4you_android.data.Resource
import com.example.fit4you_android.data.model.request.SignInReq
import com.example.fit4you_android.data.model.response.SignInRes
import com.example.fit4you_android.data.repository.auth.AuthRepository
import com.example.fit4you_android.ui.base.BaseViewModel
import com.example.fit4you_android.util.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository) :
    BaseViewModel() {

    private val _signInProcess = MutableLiveData<Resource<SignInRes>>()
    val signInProcess: LiveData<Resource<SignInRes>>
        get() = _signInProcess

    private val _showToast = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>>
        get() = _showToast

    fun doSignIn(email: String, password: String) {
        viewModelScope.launch {
            _signInProcess.value = Resource.Loading()
            authRepository.signIn(body = SignInReq(email, password)).collect {
                _signInProcess.value = it
                Log.d("SignInViewModel", _signInProcess.value.toString())
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