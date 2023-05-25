package com.example.fit4you_android.data.remote.user

import com.example.fit4you_android.data.Resource
import com.example.fit4you_android.data.api.UserService
import com.example.fit4you_android.data.dto.request.BaseQuestionReq
import com.example.fit4you_android.data.error.ErrorManager
import com.example.fit4you_android.data.error.NO_INTERNET_CONNECTION
import com.example.fit4you_android.data.dto.request.IsEmailDupReq
import com.example.fit4you_android.data.dto.request.IsNicknameDupReq
import com.example.fit4you_android.data.dto.request.SignUpReq
import com.example.fit4you_android.data.dto.response.*
import com.example.fit4you_android.network.NetworkConnectivity
import com.google.gson.Gson
import java.io.IOException
import javax.inject.Inject

class UserRemoteData @Inject constructor(
    private val networkConnectivity: NetworkConnectivity,
    private val errorManager: ErrorManager,
    private val userService: UserService
) : UserRemoteDataSource {
    override fun postIsEmailDup(body: IsEmailDupReq): Resource<IsEmailDupRes> {
        if (!networkConnectivity.isConnected()) {
            return Resource.Error(errorManager.getError(NO_INTERNET_CONNECTION).description)
        }
        return try {
            val response = userService.postIsEmailDup(body).execute()
            if (response.isSuccessful)
                Resource.Success(response.body()!!)
            else {
                Resource.Error(response.message())
            }
        } catch (e: IOException) {
            Resource.Error(e.message)
        }
    }

    override fun postIsNicknameDup(body: IsNicknameDupReq): Resource<IsNicknameDupRes> {
        if (!networkConnectivity.isConnected()) {
            return Resource.Error(errorManager.getError(NO_INTERNET_CONNECTION).description)
        }
        return try {
            val response = userService.postIsNicknameDup(body).execute()
            if (response.isSuccessful)
                Resource.Success(response.body()!!)
            else {
                Resource.Error(response.message())
            }
        } catch (e: IOException) {
            Resource.Error(e.message)
        }
    }

    override fun postSignUp(body: SignUpReq): Resource<SignUpRes> {
        if (!networkConnectivity.isConnected()) {
            return Resource.Error(errorManager.getError(NO_INTERNET_CONNECTION).description)
        }

        return try {
            val response = userService.postSignUp(body).execute()
            if (response.isSuccessful) {
                val successResponse = response.body()
                Resource.Success(successResponse!!)
            } else {
                val errorResponse =
                    Gson().fromJson(response.errorBody()?.charStream(), ErrorRes::class.java)
                Resource.Error(errorResponse.toString())
            }
        } catch (e: IOException) {
            Resource.Error(e.message)
        }
    }

    override fun postSurvey(body: BaseQuestionReq): Resource<BaseQuestionRes> {
        if (!networkConnectivity.isConnected()) {
            return Resource.Error(errorManager.getError(NO_INTERNET_CONNECTION).description)
        }
        return try {
            val response = userService.postSurvey(baseQuesionReq = body).execute()
            if (response.isSuccessful) {
                val successResponse = response.body()
                Resource.Success(successResponse!!)
            } else {
                val errorResponse =
                    Gson().fromJson(response.errorBody()?.charStream(), ErrorRes::class.java)
                Resource.Error(errorResponse.toString())
            }
        } catch (e: IOException) {
            Resource.Error(e.message)
        }
    }
}
