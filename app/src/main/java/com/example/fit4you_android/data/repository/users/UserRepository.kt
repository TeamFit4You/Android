package com.example.fit4you_android.data.repository.users

import com.example.fit4you_android.data.Resource
import com.example.fit4you_android.data.dto.request.*
import com.example.fit4you_android.data.dto.response.*
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.http.Query

interface UserRepository {
    suspend fun signUp(body: SignUpReq): Flow<Resource<SignUpRes>>
    suspend fun isNicknameDup(body: IsNicknameDupReq): Flow<Resource<IsNicknameDupRes>>
    suspend fun isEmailDup(body: IsEmailDupReq): Flow<Resource<IsEmailDupRes>>
    suspend fun postSurvey(body: BaseQuestionReq): Flow<Resource<Unit>>
    suspend fun getTodayList(token: String, query: String): Flow<Resource<TodayListRes>>
    suspend fun getRecomList(token: String, query: RecomListReq): Flow<Resource<List<RecomListRes>>>
    suspend fun getTodayString(token: String, workoutId: Long): Flow<Resource<StringListRes>>
    suspend fun getTodayVideo(token: String, workoutId: Long): Flow<Resource<ResponseBody>>
    suspend fun getExpertVideo(token: String, exerciseId: Long): Flow<Resource<ResponseBody>>
}
