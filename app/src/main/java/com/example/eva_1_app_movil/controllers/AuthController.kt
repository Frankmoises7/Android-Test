package com.example.eva_1_app_movil.controllers

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import com.example.eva_1_app_movil.ClientsActivity
import com.example.eva_1_app_movil.DashboardActivity
import com.example.eva_1_app_movil.dao.clientDAO
import com.example.eva_1_app_movil.lib.AppDatabase
import com.example.eva_1_app_movil.lib.BCrypt
import com.example.eva_1_app_movil.models.Admin
import com.example.eva_1_app_movil.models.AdminEntity
import com.example.eva_1_app_movil.models.Client2
import com.example.eva_1_app_movil.models.ClientEntity
import java.lang.Exception



class AuthController constructor(ctx: Context){
    private val INCORRECT_CREDENTIALS = "Credenciales incorrectas"
    private val ctx = ctx
    private val dao = databaseBuilder(
        ctx,
        AppDatabase::class.java, "IronBoxFitness-app3"
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
        .adminDao()

    fun login(email: String, password: String){
        val admin = dao.findByEmail(email)

        if (admin == null) {
            Toast.makeText(this.ctx, INCORRECT_CREDENTIALS, Toast.LENGTH_SHORT).show()
            return
        }

        if (BCrypt.checkpw(password, admin.password )) {
            Toast.makeText(this.ctx, "Bienvenido ${admin.userName}", Toast.LENGTH_SHORT).show()
            val intent = Intent(this.ctx, DashboardActivity::class.java)
            this.ctx.startActivity(intent)
            (this.ctx as Activity).finish()
        } else {
            Toast.makeText(this.ctx, INCORRECT_CREDENTIALS, Toast.LENGTH_SHORT).show()
        }
    }

    fun registerAdmin(admin: Admin){
        val hashedPassword = BCrypt.hashpw(admin.password, BCrypt.gensalt())
        val adminEntity = AdminEntity(
            id = null,
            userName = admin.userName,
            email = admin.email,
            password = hashedPassword
        )

        try {
            dao.insert(adminEntity)
            Toast.makeText(this.ctx, "Nuevo admin '${admin.userName}' Registrado", Toast.LENGTH_SHORT).show()
            val intent = Intent(this.ctx, ClientsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            this.ctx.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this.ctx, "Cuenta existente", Toast.LENGTH_SHORT).show()
        }
    }


    fun registerClient(client: Client2){
        val hashedPassword = BCrypt.hashpw(client.password, BCrypt.gensalt())
        val clientEntity = ClientEntity(
            id = null,
            userName = client.userName,
            email = client.email,
            password = hashedPassword,
            planStart = client.planStart,
            planType = client.planType
        )

        val db = databaseBuilder(
            ctx.applicationContext,
            AppDatabase::class.java, "IronBoxFitness-app3"
        )
            .allowMainThreadQueries()
            .build()

        val dao = db.clientDao()

        try {
            dao.insert(clientEntity)
            Toast.makeText(this.ctx, "Nuevo cliente '${client.userName}' Registrado", Toast.LENGTH_SHORT).show()
            val intent = Intent(this.ctx, ClientsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            this.ctx.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this.ctx, "Cuenta existente", Toast.LENGTH_SHORT).show()
        }
    }

}