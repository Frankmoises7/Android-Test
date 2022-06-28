package com.example.eva_1_app_movil.lib

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.eva_1_app_movil.dao.UserClientDAO
import com.example.eva_1_app_movil.models.UserClientEntity
import com.example.eva_1_app_movil.utils.Converters

@Database(entities = [UserClientEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class) //se encargará de convertir el dato a lo que tiene que convertirse
abstract class AppDatabase : RoomDatabase() {
    abstract fun userClientDao(): UserClientDAO

    companion object {
        const val DATABASE_NAME = "ironfitness-app"
    }
}