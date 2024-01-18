package com.khlopov.medicapp.data.api

import com.khlopov.medicapp.data.entity.MessageDto
import com.khlopov.medicapp.data.entity.SendCodeDto
import com.khlopov.medicapp.data.entity.TokenDto
import com.khlopov.medicapp.data.entity.UserLoginDto
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("/serk87/APIfood/FRBHWRIOJAFIDSNKJF/api/sendCode")
    suspend fun sendCode(@Body dto: SendCodeDto): MessageDto

    @POST("/serk87/APIfood/FRBHWRIOJAFIDSNKJF/api/signin")
    suspend fun login(@Body dto: UserLoginDto): TokenDto
}