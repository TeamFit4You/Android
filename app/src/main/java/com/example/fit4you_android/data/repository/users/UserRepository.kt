package com.example.fit4you_android.data.repository.users

import com.example.fit4you_android.data.Resource
import com.example.fit4you_android.data.model.request.IsEmailDupReq
import com.example.fit4you_android.data.model.request.IsNicknameDupReq
import com.example.fit4you_android.data.model.request.SignUpReq
import com.example.fit4you_android.data.model.response.IsEmailDupRes
import com.example.fit4you_android.data.model.response.IsNicknameDupRes
import com.example.fit4you_android.data.model.response.SignUpRes
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun signUp(body: SignUpReq): Flow<Resource<SignUpRes>>
    suspend fun isNicknameDup(body: IsNicknameDupReq): Flow<Resource<IsNicknameDupRes>>
    suspend fun isEmailDup(body: IsEmailDupReq): Flow<Resource<IsEmailDupRes>>
}
