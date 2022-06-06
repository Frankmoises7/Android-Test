package com.example.eva_1_app_movil.controllers

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.eva_1_app_movil.DashboardActivity

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
}