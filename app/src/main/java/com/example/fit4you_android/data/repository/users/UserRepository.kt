package com.example.fit4you_android.data.repository.users

import com.example.fit4you_android.data.Resource
import com.example.fit4you_android.data.dto.request.BaseQuestionReq
import com.example.fit4you_android.data.dto.request.IsEmailDupReq
import com.example.fit4you_android.data.dto.request.IsNicknameDupReq
import com.example.fit4you_android.data.dto.request.SignUpReq
import com.example.fit4you_android.data.dto.response.BaseQuestionRes
import com.example.fit4you_android.data.dto.response.IsEmailDupRes
import com.example.fit4you_android.data.dto.response.IsNicknameDupRes
import com.example.fit4you_android.data.dto.response.SignUpRes
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun signUp(body: SignUpReq): Flow<Resource<SignUpRes>>
    suspend fun isNicknameDup(body: IsNicknameDupReq): Flow<Resource<IsNicknameDupRes>>
    suspend fun isEmailDup(body: IsEmailDupReq): Flow<Resource<IsEmailDupRes>>
    suspend fun postSurvey(body: BaseQuestionReq): Flow<Resource<BaseQuestionRes>>
}
