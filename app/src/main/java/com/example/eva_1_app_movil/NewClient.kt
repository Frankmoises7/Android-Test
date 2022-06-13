package com.example.eva_1_app_movil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import com.example.eva_1_app_movil.controllers.AuthController
import com.example.eva_1_app_movil.utils.TilValidator
import com.google.android.material.textfield.TextInputLayout

class NewClient : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_client)

        val tilUserName = findViewById<TextInputLayout>(R.id.new_client_activity_til_userName)
        val tilEmail = findViewById<TextInputLayout>(R.id.new_client_activity_til_email)
        val tilPassword = findViewById<TextInputLayout>(R.id.new_client_activity_til_password)
        val btnRegister = findViewById<Button>(R.id.new_client_activity_btn_register)
        val spnPlan = findViewById<Spinner>(R.id.new_client_activity_spn_plan)



        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.planes_array,
            android.R.layout.simple_spinner_dropdown_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnPlan.adapter = adapter


        btnRegister.setOnClickListener {
            val email = tilEmail.editText?.text.toString()
            val userName = tilUserName.editText?.text.toString()
            val password = tilPassword.editText?.text.toString()
            val plan = spnPlan.selectedItem.toString()

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


            // Toast para revisar que el Spinner esta funcionando
            //Toast.makeText(this, plan, Toast.LENGTH_SHORT).show()

            if (emailValid && userNameValid && passwordValid){
                AuthController(this).registerUser(userName, email, password, plan)
                this.finish()
            }
        }
    }
}