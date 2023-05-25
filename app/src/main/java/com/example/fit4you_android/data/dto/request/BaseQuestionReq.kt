package com.example.fit4you_android.data.dto.request

data class BaseQuestionReq(
    var email: String,
    var neck: Int,
    var shoulder: Int,
    var lumbar: Int,
    var wrist: Int,
    var elbow: Int,
    var knee: Int,
    var hist: List<String>
)
