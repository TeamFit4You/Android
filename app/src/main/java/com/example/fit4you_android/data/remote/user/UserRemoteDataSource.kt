package com.example.fit4you_android.data.remote.user

import com.example.fit4you_android.data.Resource
import com.example.fit4you_android.data.dto.request.*
import com.example.fit4you_android.data.dto.response.*
import okhttp3.ResponseBody
import retrofit2.http.Query

interface UserRemoteDataSource {
    fun postSignUp(body: SignUpReq): Resource<SignUpRes>
    fun postIsNicknameDup(body: IsNicknameDupReq): Resource<IsNicknameDupRes>
    fun postIsEmailDup(body: IsEmailDupReq): Resource<IsEmailDupRes>
    fun postSurvey(body: BaseQuestionReq): Resource<Unit>
    fun getTodayList(token: String, query: String): Resource<TodayListRes>
    fun getRecomList(token: String, query: RecomListReq): Resource<List<RecomListRes>>
    fun getTodayString(token: String, workoutId: Long): Resource<StringListRes>
    fun getTodayVideo(token: String, workoutId: Long): Resource<ResponseBody>
    fun getExpertVideo(token: String, exerciseId: Long): Resource<ResponseBody>
}
