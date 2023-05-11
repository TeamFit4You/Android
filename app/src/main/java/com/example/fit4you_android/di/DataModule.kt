package com.example.fit4you_android.di

import com.example.fit4you_android.data.repository.auth.AuthRepository
import com.example.fit4you_android.data.repository.auth.AuthRepositoryImpl
import com.example.fit4you_android.data.repository.users.UserRepository
import com.example.fit4you_android.data.repository.users.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun provideUserRepository(
        userRepositoryImpl: UserRepositoryImpl,
    ): UserRepository

    // auths
    @Binds
    fun provideAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl,
    ): AuthRepository
}
