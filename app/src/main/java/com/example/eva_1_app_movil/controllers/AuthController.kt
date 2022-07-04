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
import com.example.eva_1_app_movil.lib.RetrofitClient
import com.example.eva_1_app_movil.models.*
import com.example.eva_1_app_movil.services.AuthService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class AuthController constructor(ctx: Context){
    private val sharedPref = ctx.getSharedPreferences("IronBoxFitness_app3", Context.MODE_PRIVATE)
    private val INCORRECT_CREDENTIALS = "Credenciales incorrectas"
    private val ctx = ctx
    private val retrofit = RetrofitClient.getRetrofitInstance()
    private val authService = retrofit.create(AuthService::class.java)

    private val dao = databaseBuilder(
        ctx,
        AppDatabase::class.java, "IronBoxFitness-app3"
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
        .adminDao()

    fun login(email: String, password: String) {
        val loginPayload = loginPayloadDTO(email, password)
        val call = authService.login(loginPayload)

        call.enqueue(object : Callback<LoginResponseDTO> {
            override fun onFailure(call: Call<LoginResponseDTO>, t: Throwable) {
                Toast.makeText(ctx, "Error de conexión", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<LoginResponseDTO>,
                response: Response<LoginResponseDTO>
            ) {
                val bodyResponse = response.body()
                if (response.code() != 200) {
                    Toast.makeText(ctx, INCORRECT_CREDENTIALS, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(
                        ctx,
                        "Bienvenido ${response.body()?.user?.username}",
                        Toast.LENGTH_LONG
                    ).show()
                    val sharedEdit = sharedPref.edit()
                    sharedEdit.putLong("user_id", bodyResponse?.user?.id!!)
                    sharedEdit.putString("user_jwt", bodyResponse?.jwt!!)
                    sharedEdit.apply()
                    val intent = Intent(ctx, Splash::class.java)
                    ctx.startActivity(intent)
                    (ctx as Activity).finish()
                }
            }
        })
    }

    // ____________________ LOGIN EN LOCAL _______________________
        /*
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
        }*/

    // ____________________ LOGIN EN LOCAL _______________________


    fun registerAdmin(admin: Admin) {

        val registerPayload = RegisterPayloadDTO(
            admin.userName,
            admin.email,
            admin.password
        )

        val call = authService.register(registerPayload)

        call.enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(ctx, "Error de conexión", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>
            ) {
                if (response.code() != 200) {
                    Toast.makeText(ctx, "Cuenta existente", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(ctx, "Cuenta registrada", Toast.LENGTH_SHORT).show()
                    val intent = Intent(ctx, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    ctx.startActivity(intent)
                }
            }
        })
    }

    // ____________________ REGISTER EN LOCAL _______________________

        /* val hashedPassword = BCrypt.hashpw(admin.password, BCrypt.gensalt())
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
        } */
    // ____________________ REGISTER EN LOCAL _______________________



    fun checkAdminSession() {
        val id = sharedPref.getLong("user_id", -1)

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
        editor.remove("user_id")
        editor.remove("user_jwt")
        editor.commit()
        val intent = Intent(this.ctx, MainActivity::class.java)
        this.ctx.startActivity(intent)
        (this.ctx as Activity).finish()
    }
}