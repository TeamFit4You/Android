package com.example.fit4you_android.data.repository.auth

import com.example.fit4you_android.data.Resource
import com.example.fit4you_android.data.dto.request.SignInReq
import com.example.fit4you_android.data.dto.response.SignInRes
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun refreshToken()
    suspend fun signIn(body: SignInReq): Flow<Resource<SignInRes>>
}
