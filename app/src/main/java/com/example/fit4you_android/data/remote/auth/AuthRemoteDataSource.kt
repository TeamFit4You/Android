package com.example.fit4you_android.data.remote.auth

import com.example.fit4you_android.data.Resource
import com.example.fit4you_android.data.model.request.RefreshTokenReq
import com.example.fit4you_android.data.model.request.SignInReq
import com.example.fit4you_android.data.model.response.RefreshTokenRes
import com.example.fit4you_android.data.model.response.SignInRes


interface AuthRemoteDataSource {
    fun postSignIn(body: SignInReq): Resource<SignInRes>
    fun getRefreshToken(body: RefreshTokenReq): Resource<RefreshTokenRes>
}
