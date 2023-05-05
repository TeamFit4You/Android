package com.example.fit4you_android.data.remote.auth

import com.example.fit4you_android.data.Resource
import com.example.fit4you_android.data.api.AuthService
import com.example.fit4you_android.data.error.ErrorManager
import com.example.fit4you_android.data.error.NO_INTERNET_CONNECTION
import com.example.fit4you_android.data.model.request.RefreshTokenReq
import com.example.fit4you_android.data.model.request.SignInReq
import com.example.fit4you_android.data.model.response.RefreshTokenRes
import com.example.fit4you_android.data.model.response.SignInRes
import com.example.fit4you_android.network.NetworkConnectivity
import java.io.IOException
import javax.inject.Inject

class AuthRemoteData @Inject constructor(
    private val networkConnectivity: NetworkConnectivity,
    private val errorManager: ErrorManager,
    private val authService: AuthService
) : AuthRemoteDataSource {
    override fun getRefreshToken(body: RefreshTokenReq): Resource<RefreshTokenRes> {
        TODO("NOT IMPLEMENTED YET")
    }

    override fun postSignIn(body: SignInReq): Resource<SignInRes> {
        if (!networkConnectivity.isConnected()) {
            return Resource.Error(errorManager.getError(NO_INTERNET_CONNECTION).description)
        }

        return try {
            val response = authService.postSignIn(signInReq = body).execute()
            if (response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: IOException) {
            Resource.Error(e.message)
        }
    }
}
