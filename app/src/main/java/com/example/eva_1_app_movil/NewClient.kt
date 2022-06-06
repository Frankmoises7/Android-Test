package com.example.eva_1_app_movil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.eva_1_app_movil.utils.TilValidator
import com.google.android.material.textfield.TextInputLayout

class NewClient : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_client)

        val tilUserName = findViewById<TextInputLayout>(R.id.new_client_activity_til_userName)
        val tilEmail = findViewById<TextInputLayout>(R.id.new_client_activity_til_email)
        val btnRegister = findViewById<Button>(R.id.new_client_activity_btn_register)

        btnRegister.setOnClickListener {

            val emailValid = TilValidator(tilEmail)
                .required()
                .email()
                .isValid()

            val userNameValid = TilValidator(tilUserName)
                .required()
                .isValid()

            if (emailValid && userNameValid){
                Toast.makeText(this, "Nuevo Cliente creado (se verá reflejado en una proxima actualizacion)", Toast.LENGTH_SHORT).show()
                this.finish()
            }
        }
    }
}