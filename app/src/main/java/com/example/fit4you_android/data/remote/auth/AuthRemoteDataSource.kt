package com.example.fit4you_android.data.remote.auth

import com.example.fit4you_android.data.Resource
import com.example.fit4you_android.data.dto.request.RefreshTokenReq
import com.example.fit4you_android.data.dto.request.SignInReq
import com.example.fit4you_android.data.dto.response.RefreshTokenRes
import com.example.fit4you_android.data.dto.response.SignInRes


interface AuthRemoteDataSource {
    fun postSignIn(body: SignInReq): Resource<SignInRes>
    fun getRefreshToken(body: RefreshTokenReq): Resource<RefreshTokenRes>
}
