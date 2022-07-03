package com.example.eva_1_app_movil.controllers

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.widget.Toast
import androidx.room.Room.databaseBuilder
import com.example.eva_1_app_movil.*
import com.example.eva_1_app_movil.lib.AppDatabase
import com.example.eva_1_app_movil.lib.BCrypt
import com.example.eva_1_app_movil.models.Admin
import com.example.eva_1_app_movil.models.AdminEntity
import java.lang.Exception



class AuthController constructor(ctx: Context){
    private val sharedPref = ctx.getSharedPreferences("IronBoxFitness_app", Context.MODE_PRIVATE)
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
            val sharedEdit = sharedPref.edit()
            sharedEdit.putLong("admin_id", admin.id!!)
            sharedEdit.apply()
            val intent = Intent(this.ctx, Splash::class.java)
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


    fun checkAdminSession() {
        val id = sharedPref.getLong("admin_id", -1)

        Handler().postDelayed({
            if (id == (-1).toLong()) {
                val intent = Intent(this.ctx, MainActivity::class.java)
                this.ctx.startActivity(intent)
            } else {
                val intent = Intent(this.ctx, DashboardActivity::class.java)
                this.ctx.startActivity(intent)
            }
            (this.ctx as Activity).finish()
        }, 2000)
    }

    fun clearSession() {
        val editor = sharedPref.edit()
        editor.remove("admin_id")
        editor.commit()
        val intent = Intent(this.ctx, MainActivity::class.java)
        this.ctx.startActivity(intent)
        (this.ctx as Activity).finish()
    }
}