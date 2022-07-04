package com.example.eva_1_app_movil.models

data class loginPayloadDTO (
    val identifier: String,
    val password: String
)

data class UserDTO (
    val id: Long,
    val username: String
        )


data class LoginResponseDTO (
    val jwt: String,
    val user: UserDTO
    )

data class RegisterPayloadDTO (
    val username: String,
    val email: String,
    val password: String
        )