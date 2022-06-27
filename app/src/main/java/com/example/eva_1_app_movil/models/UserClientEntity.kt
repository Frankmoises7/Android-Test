package com.example.eva_1_app_movil.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "usersClients", indices = [Index(value = ["email"], unique = true)])
data class UserClientEntity(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    @ColumnInfo(name = "user_name") val userName: String,
    val email: String,
    val planType: String,
    val password: String,
    val planStart: Date
)
