package com.example.fit4you_android.data.dto.response

import com.google.gson.annotations.SerializedName

data class SignInRes(
    @SerializedName("memberId") val memberId: Int,
    @SerializedName("authorization") val authToken: String
)
