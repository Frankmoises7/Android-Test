package com.example.eva_1_app_movil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.eva_1_app_movil.controllers.AuthController
import com.example.eva_1_app_movil.utils.TilValidator
import com.google.android.material.textfield.TextInputLayout

class RecoveryPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recovey_password)

        val btnRecoveryPassword = findViewById<Button>(R.id.recovery_password_activity_btn_recoveryPassword)
        val tilEmail = findViewById<TextInputLayout>(R.id.recovery_password_activity_til_email)
        val tvToLogin = findViewById<TextView>(R.id.recovery_password_activity_tv_goToLogin)

        btnRecoveryPassword.setOnClickListener {
            val emailValid = TilValidator(tilEmail)
                .required()
                .email()
                .isValid()

            if (emailValid) {
                val controller = AuthController(this)
                Toast.makeText(this, "Revisa tu casilla de correo", Toast.LENGTH_LONG).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                this.finish()
            } else {
                Toast.makeText(this, "Campos Invalidas", Toast.LENGTH_SHORT).show()
            }
        }

        tvToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
}