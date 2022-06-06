package com.example.eva_1_app_movil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.eva_1_app_movil.utils.TilValidator
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnRegister = findViewById<Button>(R.id.register_activity_btn_register)
        val tilUserName = findViewById<TextInputLayout>(R.id.register_activity_til_userName)
        val tilEmail = findViewById<TextInputLayout>(R.id.register_activity_til_email)
        val tilPassword = findViewById<TextInputLayout>(R.id.register_activity_til_password)

        btnRegister.setOnClickListener {
            val emailValid = TilValidator(tilEmail)
                .required()
                .email()
                .isValid()

            val userNameValid = TilValidator(tilUserName)
                .required()
                .isValid()

            val passwordValid = TilValidator(tilPassword)
                .required()
                .isValid()


            if (emailValid && userNameValid && passwordValid){
                Toast.makeText(this, "Nuevo Admin creado", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                this.finish()
            }
        }

        val tvToLogin = findViewById<TextView>(R.id.register_activity_tv_goToLogin)
        tvToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}