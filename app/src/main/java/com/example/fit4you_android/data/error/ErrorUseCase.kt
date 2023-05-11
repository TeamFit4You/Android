package com.example.fit4you_android.data.error

interface ErrorUseCase {
    fun getError(errorCode: Int): Error
}
