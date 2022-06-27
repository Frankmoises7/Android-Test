package com.example.eva_1_app_movil.controllers

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.room.Room
import com.example.eva_1_app_movil.ClientsActivity
import com.example.eva_1_app_movil.DashboardActivity
import com.example.eva_1_app_movil.dao.UserClientDAO
import com.example.eva_1_app_movil.lib.AppDatabase
import com.example.eva_1_app_movil.lib.BCrypt
import com.example.eva_1_app_movil.models.UserClient
import com.example.eva_1_app_movil.models.UserClientEntity

val INCORRECT_CREDENTIALS = "Credenciales incorrectas"
class AuthController constructor(ctx: Context){
    private val ctx = ctx
    private val dao = Room.databaseBuilder(
        ctx.applicationContext,
        AppDatabase::class.java, "database-name"
    )
        .allowMainThreadQueries()
        .build()
        .userClientDao()

    fun login(email: String, password: String){
        val username = dao.findByEmail(email)

        if(username == null){
            Toast.makeText(this.ctx, INCORRECT_CREDENTIALS, Toast.LENGTH_SHORT).show()
            return
        }

        if (BCrypt.checkpw(password, username.password)) {
            Toast.makeText(this.ctx, "Bienvenido ${username.userName}", Toast.LENGTH_SHORT).show()
            val intent = Intent(this.ctx, DashboardActivity::class.java)
            this.ctx.startActivity(intent)
            (this.ctx as Activity).finish()
        } else {
            Toast.makeText(this.ctx, INCORRECT_CREDENTIALS, Toast.LENGTH_SHORT).show()
        }
    }

    fun registerUser(user: UserClient){
        val hashedPassword = BCrypt.hashpw(user.password, BCrypt.gensalt())
        val userClientEntity = UserClientEntity(
            id = null,
            userName = user.userName,
            email = user.email,
            planType = user.planType,
            password = hashedPassword,
            planStart = user.planStart
        )
        try{
            dao.insert(userClientEntity)

            Toast.makeText(this.ctx, "Usuario ${user.userName} Registrado", Toast.LENGTH_SHORT).show()
            val intent = Intent(this.ctx, ClientsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            //this.ctx.startActivity(intent)
        }catch (e: Exception){
            Toast.makeText(this.ctx, "Usuario ${user.email} ya se encuentra registrado", Toast.LENGTH_LONG).show()
        }



    }
}