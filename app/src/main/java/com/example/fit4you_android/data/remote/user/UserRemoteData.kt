package com.example.fit4you_android.data.remote.user

import android.util.Log
import com.example.fit4you_android.data.Resource
import com.example.fit4you_android.data.api.UserService
import com.example.fit4you_android.data.dto.request.*
import com.example.fit4you_android.data.error.ErrorManager
import com.example.fit4you_android.data.error.NO_INTERNET_CONNECTION
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

    override fun postSurvey(body: BaseQuestionReq): Resource<Unit> {
        if (!networkConnectivity.isConnected()) {
            return Resource.Error(errorManager.getError(NO_INTERNET_CONNECTION).description)
        }
        return try {
            val response = userService.postSurvey(baseQuesionReq = body).execute()
            Log.d("remoteData_res","$response")
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

    override fun getTodayList(token: String, query: String): Resource<TodayListRes> {
        if (!networkConnectivity.isConnected()) {
            return Resource.Error(errorManager.getError(NO_INTERNET_CONNECTION).description)
        }
        return try {
            val response = userService.getTodayList(token, query).execute()
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

    override fun getRecomList(token: String, query: RecomListReq): Resource<List<RecomListRes>> {
        if (!networkConnectivity.isConnected()) {
            return Resource.Error(errorManager.getError(NO_INTERNET_CONNECTION).description)
        }
        return try {
            val response = userService.getRecomList(token, query.email, query.part).execute()
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

    override fun getTodayString(token: String, workoutId: Long): Resource<StringListRes> {
        if (!networkConnectivity.isConnected()) {
            return Resource.Error(errorManager.getError(NO_INTERNET_CONNECTION).description)
        }
        return try {
            val response = userService.getStringList(token, workoutId).execute()
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
