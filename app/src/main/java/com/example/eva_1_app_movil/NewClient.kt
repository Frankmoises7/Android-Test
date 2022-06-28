package com.example.eva_1_app_movil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import com.example.eva_1_app_movil.controllers.AuthController
import com.example.eva_1_app_movil.models.UserClient
import com.example.eva_1_app_movil.utils.TilValidator
import com.example.eva_1_app_movil.utils.showDatePickerDialog
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

class NewClient : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_client)

        val tilUserName = findViewById<TextInputLayout>(R.id.new_client_activity_til_userName)
        val tilEmail = findViewById<TextInputLayout>(R.id.new_client_activity_til_email)
        val tilPassword = findViewById<TextInputLayout>(R.id.new_client_activity_til_password)
        val btnRegister = findViewById<Button>(R.id.new_client_activity_btn_register)
        val spnPlanType = findViewById<Spinner>(R.id.new_client_activity_spn_planType)
        val tilPlanStart = findViewById<TextInputLayout>(R.id.new_client_activity_til_planStart)

        //seteo de adapter para la creacion del Spinner - Esto permite usar los recursos que estan en Values
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.planes_array,
            android.R.layout.simple_spinner_dropdown_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnPlanType.adapter = adapter


        //Uso del TIL para DatePicker
        tilPlanStart.editText?.setOnClickListener { _ ->
            showDatePickerDialog(this, tilPlanStart, Date())
        }

        btnRegister.setOnClickListener {
            val email = tilEmail.editText?.text.toString()
            val userName = tilUserName.editText?.text.toString()
            val password = tilPassword.editText?.text.toString()
            val planType = spnPlanType.selectedItem.toString()
            val planStart = tilPlanStart.editText?.text.toString()

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

            val planStartValid = TilValidator(tilPlanStart)
                .required()
                .DateBefore(Date())
                .isValid()

            // Toast para revisar que el Spinner esta funcionando
            //Toast.makeText(this, plan, Toast.LENGTH_SHORT).show()

            if (emailValid && userNameValid && passwordValid && planStartValid){
                val user = UserClient(
                    id = null,
                    userName = userName,
                    email = email,
                    planType = planType,
                    password = password,
                    planStart = SimpleDateFormat("yyyy-MM-dd").parse(planStart)
                )
                AuthController(this).registerUser(user)
                this.finish()
            }
            else {
                Toast.makeText(this, "Campos inválidos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}