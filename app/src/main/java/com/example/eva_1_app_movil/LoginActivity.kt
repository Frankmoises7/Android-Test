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


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.login_activity_btn_login)
        val tvRecoveryPassWord = findViewById<TextView>(R.id.login_activity_tv_recoveryPassword)
        val tilEmail = findViewById<TextInputLayout>(R.id.login_activity_til_email)
        val tilPassword = findViewById<TextInputLayout>(R.id.login_activity_til_password)


        btnLogin.setOnClickListener {
           val email = tilEmail.editText?.text.toString()
           val password = tilPassword.editText?.text.toString()

            val emailValid = TilValidator(tilEmail)
                .required()
                .email()
                .isValid()

            val passwordValid = TilValidator(tilPassword)
                .required()
                .isValid()

            if (emailValid && passwordValid) {
                val controller = AuthController(this)
                controller.login(email, password)
            } else {
                Toast.makeText(this, "Campos Invalidos", Toast.LENGTH_SHORT).show()
            }
        }

        tvRecoveryPassWord.setOnClickListener {
            val intent = Intent(this, RecoveryPasswordActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
}