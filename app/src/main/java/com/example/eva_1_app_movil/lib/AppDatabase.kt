package com.example.eva_1_app_movil.lib


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.eva_1_app_movil.dao.adminDAO
import com.example.eva_1_app_movil.dao.clientDAO
import com.example.eva_1_app_movil.models.AdminEntity
import com.example.eva_1_app_movil.models.ClientEntity
import com.example.eva_1_app_movil.utils.Converters

@Database(entities = [AdminEntity::class, ClientEntity::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)

abstract class AppDatabase : RoomDatabase() {
    abstract fun adminDao(): adminDAO
    abstract fun clientDao(): clientDAO


    companion object {
        const val DATABASE_NAME = "IronBoxFitness-app3"
    }
}