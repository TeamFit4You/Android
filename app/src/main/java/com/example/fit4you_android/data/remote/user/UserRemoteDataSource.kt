package com.example.fit4you_android.data.remote.user

import com.example.fit4you_android.data.Resource
import com.example.fit4you_android.data.model.request.IsEmailDupReq
import com.example.fit4you_android.data.model.request.IsNicknameDupReq
import com.example.fit4you_android.data.model.request.SignUpReq
import com.example.fit4you_android.data.model.response.IsEmailDupRes
import com.example.fit4you_android.data.model.response.IsNicknameDupRes
import com.example.fit4you_android.data.model.response.SignUpRes

interface UserRemoteDataSource {
    fun postSignUp(body: SignUpReq): Resource<SignUpRes>
    fun postIsNicknameDup(body: IsNicknameDupReq): Resource<IsNicknameDupRes>
    fun postIsEmailDup(body: IsEmailDupReq): Resource<IsEmailDupRes>
}
