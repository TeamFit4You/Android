package com.example.fit4you_android.data.remote.user

import com.example.fit4you_android.data.Resource
import com.example.fit4you_android.data.dto.request.BaseQuestionReq
import com.example.fit4you_android.data.dto.request.IsEmailDupReq
import com.example.fit4you_android.data.dto.request.IsNicknameDupReq
import com.example.fit4you_android.data.dto.request.SignUpReq
import com.example.fit4you_android.data.dto.response.BaseQuestionRes
import com.example.fit4you_android.data.dto.response.IsEmailDupRes
import com.example.fit4you_android.data.dto.response.IsNicknameDupRes
import com.example.fit4you_android.data.dto.response.SignUpRes

interface UserRemoteDataSource {
    fun postSignUp(body: SignUpReq): Resource<SignUpRes>
    fun postIsNicknameDup(body: IsNicknameDupReq): Resource<IsNicknameDupRes>
    fun postIsEmailDup(body: IsEmailDupReq): Resource<IsEmailDupRes>
    fun postSurvey(body: BaseQuestionReq): Resource<BaseQuestionRes>
}
