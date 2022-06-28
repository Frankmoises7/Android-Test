package com.example.eva_1_app_movil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import com.example.eva_1_app_movil.controllers.AuthController
import com.example.eva_1_app_movil.models.Admin
import com.example.eva_1_app_movil.models.AdminEntity
import com.example.eva_1_app_movil.models.Client2

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val controller = AuthController(this)
        controller.checkAdminSession()
    }
}