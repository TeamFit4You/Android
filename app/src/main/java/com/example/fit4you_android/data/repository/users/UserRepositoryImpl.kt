package com.example.fit4you_android.data.repository.users

import com.example.fit4you_android.data.Resource
import com.example.fit4you_android.data.dto.request.*
import com.example.fit4you_android.data.dto.response.*
import com.example.fit4you_android.data.remote.user.UserRemoteData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody
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

    override suspend fun getTodayList(token: String, query: String): Flow<Resource<TodayListRes>> {
        return flow<Resource<TodayListRes>> {
            emit(remoteData.getTodayList(token, query))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getRecomList(token: String, query: RecomListReq): Flow<Resource<List<RecomListRes>>> {
        return flow<Resource<List<RecomListRes>>> {
            emit(remoteData.getRecomList(token, query))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getTodayString(token: String, workoutId: Long): Flow<Resource<StringListRes>> {
        return flow<Resource<StringListRes>> {
            emit(remoteData.getTodayString(token, workoutId))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getTodayVideo(token: String, workoutId: Long): Flow<Resource<ResponseBody>> {
        return flow {
            emit(remoteData.getTodayVideo(token, workoutId))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getRecomVideo(token: String, exerciseId: Long): Flow<Resource<ResponseBody>> {
        return flow{
            emit(remoteData.getRecomVideo(token, exerciseId))
        }.flowOn(ioDispatcher)
    }
}
