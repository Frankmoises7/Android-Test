package com.example.eva_1_app_movil.models

import java.io.Serializable

data class Client(
    val id: Long?,
    val title: String,
    val description: String?
    ) : Serializable
