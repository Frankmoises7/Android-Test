package com.example.eva_1_app_movil.services

import com.example.eva_1_app_movil.models.LoginResponseDTO
import com.example.eva_1_app_movil.models.RegisterPayloadDTO
import com.example.eva_1_app_movil.models.loginPayloadDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/auth/local")
    fun login(@Body payload: loginPayloadDTO): Call<LoginResponseDTO>

    @POST("/api/auth/local/register")
    fun register(@Body payload: RegisterPayloadDTO): Call<Void>
}