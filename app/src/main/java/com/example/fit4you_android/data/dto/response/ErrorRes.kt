package com.example.fit4you_android.data.dto.response

data class ErrorRes(
    val code: String,
    val message: String,
    val validation: Map<String, String>
)
