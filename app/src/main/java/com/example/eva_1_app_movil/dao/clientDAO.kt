package com.example.eva_1_app_movil.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.eva_1_app_movil.models.ClientEntity

@Dao
interface clientDAO {
    @Query("SELECT * FROM clients")
    fun findAll(): List<ClientEntity>

    @Query("SELECT * FROM clients WHERE email = :email")
    fun findByEmail(email: String): ClientEntity?

    @Insert
    fun insert(client: ClientEntity)

    @Update
    fun update(client: ClientEntity)

    @Query("DELETE FROM clients where email = :email")
    fun delete(email: String)
}