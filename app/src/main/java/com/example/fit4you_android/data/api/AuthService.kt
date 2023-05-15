package com.example.fit4you_android.data.api

import com.example.fit4you_android.data.dto.request.SignInReq
import com.example.fit4you_android.data.dto.response.RefreshTokenRes
import com.example.fit4you_android.data.dto.response.SignInRes
import retrofit2.Call
import retrofit2.http.*

interface AuthService {
    @POST("/members/sign-in")
    fun postSignIn(
        @Body signInReq: SignInReq,
    ): Call<SignInRes>

    @GET("api/auths/{userId}")
    fun postRefreshToken(
        @Path("userId") userId: Long,
        @Query("role") role: String = "user",
        // 헤더맵
    ): Call<RefreshTokenRes>
}
