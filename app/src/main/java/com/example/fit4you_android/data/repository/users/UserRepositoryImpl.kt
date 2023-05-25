package com.example.fit4you_android.data.repository.users

import com.example.fit4you_android.data.Resource
import com.example.fit4you_android.data.dto.request.BaseQuestionReq
import com.example.fit4you_android.data.dto.request.IsEmailDupReq
import com.example.fit4you_android.data.dto.request.IsNicknameDupReq
import com.example.fit4you_android.data.dto.request.SignUpReq
import com.example.fit4you_android.data.dto.response.BaseQuestionRes
import com.example.fit4you_android.data.dto.response.IsEmailDupRes
import com.example.fit4you_android.data.dto.response.IsNicknameDupRes
import com.example.fit4you_android.data.dto.response.SignUpRes
import com.example.fit4you_android.data.remote.user.UserRemoteData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class UserRepositoryImpl @Inject constructor(
    private val remoteData: UserRemoteData,
    private val ioDispatcher: CoroutineContext
) : UserRepository {
    override suspend fun signUp(body: SignUpReq): Flow<Resource<SignUpRes>> {
        return flow {
            emit(remoteData.postSignUp(body))
        }.flowOn(ioDispatcher)
    }

    override suspend fun isNicknameDup(body: IsNicknameDupReq): Flow<Resource<IsNicknameDupRes>> {
        return flow {
            emit(remoteData.postIsNicknameDup(body))
        }.flowOn(ioDispatcher)
    }

    override suspend fun isEmailDup(body: IsEmailDupReq): Flow<Resource<IsEmailDupRes>> {
        return flow {
            emit(remoteData.postIsEmailDup(body))
        }.flowOn(ioDispatcher)
    }

    override suspend fun postSurvey(body: BaseQuestionReq): Flow<Resource<BaseQuestionRes>>{
        return flow{
            emit(remoteData.postSurvey(body = body))
        }.flowOn(ioDispatcher)
    }
}
