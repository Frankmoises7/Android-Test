package com.example.eva_1_app_movil.models

import java.io.Serializable
import java.util.*

data class Client2(
    val id: Long?,
    val userName: String,
    val email: String,
    val password: String,
    val planType: String,
    val planStart: Date?
) : Serializable
