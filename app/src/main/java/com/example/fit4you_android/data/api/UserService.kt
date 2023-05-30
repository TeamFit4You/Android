package com.example.fit4you_android.data.api

import com.example.fit4you_android.data.dto.request.*
import com.example.fit4you_android.data.dto.response.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
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
    fun getTodayList(
        @Header("authorization") accessToken: String,
        @Query("email") email: String
    ): Call<TodayListRes>

    @POST("/workouts/estimation/{workoutId}")
    fun getEstimation(
        @Header("authorization") accessToken: String,
        @Path("workoutId") workoutId: Long,
        @Query("file") file: String
    ): Call<TodayEstiRes>

    @GET("/workouts/video/{workoutId}")
    fun getTodayVideo(
        @Header("authorization") accessToken: String,
        @Path("workoutId") workoutId: Long
    ): Call<ResponseBody>

    @GET("/workouts/info/{workoutId}")
    fun getStringList(
        @Header("authorization") accessToken: String,
        @Path("workoutId") workoutId: Long
    ): Call<StringListRes>

    @GET("/exercises")
    fun getRecomList(
        @Header("authorization") accessToken: String,
        @Query("email") email: String,
        @Query("part") part: String
    ): Call<List<RecomListRes>>

    @GET("/exercises/video/{exerciseId}/stream")
    fun getExpertVideo(
        @Header("authorization") accessToken: String,
        @Path("exerciseId") exerciseId: Long
    ): Call<ResponseBody>
}
