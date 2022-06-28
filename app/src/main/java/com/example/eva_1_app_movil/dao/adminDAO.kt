package com.example.eva_1_app_movil.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.eva_1_app_movil.models.AdminEntity

@Dao
interface adminDAO {
    @Query("SELECT * FROM admins WHERE admins.email = :email LIMIT 1")
    fun findByEmail(email: String):AdminEntity?

    @Insert
    fun insert(admin: AdminEntity)
}