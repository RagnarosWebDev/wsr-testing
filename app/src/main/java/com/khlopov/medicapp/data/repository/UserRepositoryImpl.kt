package com.khlopov.medicapp.data.repository

import com.khlopov.medicapp.data.api.UserApi
import com.khlopov.medicapp.data.entity.SendCodeDto
import com.khlopov.medicapp.data.entity.TokenDto
import com.khlopov.medicapp.data.entity.UserLoginDto
import com.khlopov.medicapp.domain.repository.UserRepository
import retrofit2.Retrofit

class UserRepositoryImpl(retrofit: Retrofit) : UserRepository {

    private val _userApi = retrofit.create(UserApi::class.java);

    override suspend fun sendCode(sendCodeDto: SendCodeDto): String {
        return _userApi.sendCode(sendCodeDto).message
    }

    override suspend fun login(userLoginDto: UserLoginDto): TokenDto {
        return _userApi.login(userLoginDto)
    }
}