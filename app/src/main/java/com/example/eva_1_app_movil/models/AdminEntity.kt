package com.example.eva_1_app_movil.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "admins", indices = [Index(value = ["email"], unique = true)])
data class AdminEntity (
    @PrimaryKey(autoGenerate = true) val id: Long?,
    @ColumnInfo (name = "user_name") val userName: String,
    val email: String,
    val password: String
)