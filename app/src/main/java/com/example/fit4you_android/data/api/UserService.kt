package com.example.fit4you_android.data.api

import com.example.fit4you_android.data.dto.request.*
import com.example.fit4you_android.data.dto.response.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query as Query

interface UserService {
    @POST("/members/sign-up")
    fun postSignUp(@Body signUpRequest: SignUpReq): Call<SignUpRes>

    @POST("/api/users/email")
    fun postIsEmailDup(@Body email: IsEmailDupReq): Call<IsEmailDupRes>

    @POST("/api/users/nickname")
    fun postIsNicknameDup(@Body nickname: IsNicknameDupReq): Call<IsNicknameDupRes>

    @POST("/members/survey")
    fun postSurvey(@Body baseQuesionReq: BaseQuestionReq): Call<Unit>

    @GET("/trainings/recommend")
    fun getTodayList(@Query("email") email: String): Call<TodayListRes>

    @GET("/workouts/info/{workoutId}")
    fun getStringList(@Path("workoutId") workoutId: Long): Call<StringListRes>

    @GET("/exercises")
    fun getRecomList(
        @Query("email") email: String,
        @Query("part") part: String
    ): Call<List<RecomListRes>>
}
