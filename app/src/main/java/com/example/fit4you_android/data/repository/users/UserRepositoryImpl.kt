package com.example.fit4you_android.data.repository.users

import com.example.fit4you_android.data.Resource
import com.example.fit4you_android.data.dto.request.*
import com.example.fit4you_android.data.dto.response.*
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

    override suspend fun postSurvey(body: BaseQuestionReq): Flow<Resource<Unit>>{
        return flow{
            emit(remoteData.postSurvey(body = body))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getTodayList(query: String): Flow<Resource<TodayListRes>> {
        return flow<Resource<TodayListRes>> {
            emit(remoteData.getTodayList(query))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getRecomList(query: RecomListReq): Flow<Resource<List<RecomListRes>>> {
        return flow<Resource<List<RecomListRes>>> {
            emit(remoteData.getRecomList(query))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getTodayString(workoutId: Long): Flow<Resource<StringListRes>> {
        return flow<Resource<StringListRes>> {
            emit(remoteData.getTodayString(workoutId))
        }.flowOn(ioDispatcher)
    }
}
