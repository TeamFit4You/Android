package com.example.fit4you_android.data.api

import com.example.fit4you_android.data.model.request.IsEmailDupReq
import com.example.fit4you_android.data.model.request.IsNicknameDupReq
import com.example.fit4you_android.data.model.request.SignUpReq
import com.example.fit4you_android.data.model.response.IsEmailDupRes
import com.example.fit4you_android.data.model.response.IsNicknameDupRes
import com.example.fit4you_android.data.model.response.SignUpRes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/api/users/sign-up")
    fun postSignUp(@Body signUpRequest: SignUpReq): Call<SignUpRes>

    @POST("/api/users/email")
    fun postIsEmailDup(@Body email: IsEmailDupReq): Call<IsEmailDupRes>

    @POST("/api/users/nickname")
    fun postIsNicknameDup(@Body nickname: IsNicknameDupReq): Call<IsNicknameDupRes>
}
