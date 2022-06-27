package com.example.eva_1_app_movil.utils

import androidx.room.TypeConverter
import java.util.*

class Converters {
    //saber como trabajar con ciertos datos, por ejemplo las fechas, ej.como convertir y como las devuelven
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}