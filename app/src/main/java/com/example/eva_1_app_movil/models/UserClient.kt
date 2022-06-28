package com.example.eva_1_app_movil.models

import java.util.*

data class UserClient(
    val id: Long?,
    val userName: String,
    val email: String,
    val planType: String,
    val password: String,
    val planStart: Date
)
