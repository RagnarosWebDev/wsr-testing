package com.khlopov.medicapp.domain.repository

import com.khlopov.medicapp.data.entity.SendCodeDto
import com.khlopov.medicapp.data.entity.TokenDto
import com.khlopov.medicapp.data.entity.UserLoginDto

interface UserRepository {
    suspend fun sendCode(sendCodeDto: SendCodeDto): String
    suspend fun login(userLoginDto: UserLoginDto): TokenDto
}