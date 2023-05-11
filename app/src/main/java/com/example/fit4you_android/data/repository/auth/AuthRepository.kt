package com.example.fit4you_android.data.repository.auth

import com.example.fit4you_android.data.Resource
import com.example.fit4you_android.data.model.request.SignInReq
import com.example.fit4you_android.data.model.response.SignInRes
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun refreshToken()
    suspend fun signIn(body: SignInReq): Flow<Resource<SignInRes>>
}
