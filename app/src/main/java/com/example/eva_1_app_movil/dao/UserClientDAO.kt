package com.example.eva_1_app_movil.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.eva_1_app_movil.models.UserClientEntity

@Dao
interface UserClientDAO {
    @Query("SELECT * FROM usersClients WHERE email = :email LIMIT 1")
    fun findByEmail(email: String): UserClientEntity?

    @Insert
    fun insert(user: UserClientEntity)

    @Delete
    fun delete(user: UserClientEntity)
}