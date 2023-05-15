package com.example.fit4you_android.data.api

import com.example.fit4you_android.data.dto.request.IsEmailDupReq
import com.example.fit4you_android.data.dto.request.IsNicknameDupReq
import com.example.fit4you_android.data.dto.request.SignUpReq
import com.example.fit4you_android.data.dto.response.IsEmailDupRes
import com.example.fit4you_android.data.dto.response.IsNicknameDupRes
import com.example.fit4you_android.data.dto.response.SignUpRes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/members/sign-up")
    fun postSignUp(@Body signUpRequest: SignUpReq): Call<SignUpRes>

    @POST("/api/users/email")
    fun postIsEmailDup(@Body email: IsEmailDupReq): Call<IsEmailDupRes>

    @POST("/api/users/nickname")
    fun postIsNicknameDup(@Body nickname: IsNicknameDupReq): Call<IsNicknameDupRes>
}
