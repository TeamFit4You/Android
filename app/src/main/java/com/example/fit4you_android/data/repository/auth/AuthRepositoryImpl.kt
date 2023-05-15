package com.example.fit4you_android.data.repository.auth

import com.example.fit4you_android.data.Resource
import com.example.fit4you_android.data.dto.request.SignInReq
import com.example.fit4you_android.data.dto.response.SignInRes
import com.example.fit4you_android.data.remote.auth.AuthRemoteData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class AuthRepositoryImpl @Inject constructor(
    private val remoteData: AuthRemoteData,
    private val ioDispatcher: CoroutineContext,
) : AuthRepository {
    override suspend fun refreshToken() {

    }

    override suspend fun signIn(body: SignInReq): Flow<Resource<SignInRes>> {
        return flow {
            emit(remoteData.postSignIn(body))
        }.flowOn(ioDispatcher)
    }
}
