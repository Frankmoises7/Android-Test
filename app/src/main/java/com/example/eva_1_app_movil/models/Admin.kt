package com.example.eva_1_app_movil.models

import java.io.Serializable


data class Admin(
    val id: Long?,
    val userName: String,
    val email: String,
    val password: String
) : Serializable