package com.example.eva_1_app_movil.controllers

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.room.Room
import com.example.eva_1_app_movil.ClientsActivity
import com.example.eva_1_app_movil.DashboardActivity
import com.example.eva_1_app_movil.models.UserClient
import com.example.eva_1_app_movil.models.UserClientEntity

class AuthController constructor(ctx: Context){
    private val ctx = ctx

    fun login(email: String, password: String){
        if (email == "admin@admin.cl" && password == "123456") {
            Toast.makeText(this.ctx, "Bienvenido $email", Toast.LENGTH_SHORT).show()
            val intent = Intent(this.ctx, DashboardActivity::class.java)
            this.ctx.startActivity(intent)
        } else {
            Toast.makeText(this.ctx, "Credenciales Incorrectas", Toast.LENGTH_SHORT).show()
        }
    }

    fun registerUser(user: UserClient){
        val userClientEntity = UserClientEntity(
            id = null,
            userName = user.userName,
            email = user.email,
            planType = user.planType,
            password = user.password,
            planStart = user.planStart
        )

        val db = Room.databaseBuilder(
            ctx.applicationContext,
            AppDataBase::class.java, "database-name"
        )

        Toast.makeText(this.ctx, "Usuario ${userName} Registrado", Toast.LENGTH_SHORT).show()
        val intent = Intent(this.ctx, ClientsActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        this.ctx.startActivity(intent)
    }
}