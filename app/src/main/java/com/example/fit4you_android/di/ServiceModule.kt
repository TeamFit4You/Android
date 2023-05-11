package com.example.fit4you_android.di

import com.example.fit4you_android.data.api.AuthService
import com.example.fit4you_android.data.api.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun providesUserService(
        retrofit: Retrofit
    ): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun providesAuthService(
        retrofit: Retrofit
    ): AuthService {
        return retrofit.create(AuthService::class.java)
    }
}