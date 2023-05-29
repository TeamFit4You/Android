package com.example.fit4you_android.data.dto.request

data class BaseQuestionReq(
    var email: String,
    var neck: Float,
    var shoulder: Float,
    var lumbar: Float,
    var wrist: Float,
    var elbow: Float,
    var knee: Float,
    var hist: List<String>
)
