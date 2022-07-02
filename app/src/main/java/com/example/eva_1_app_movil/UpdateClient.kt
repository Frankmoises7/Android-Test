package com.example.eva_1_app_movil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.example.eva_1_app_movil.controllers.ClientsController
import com.example.eva_1_app_movil.models.Client2
import com.example.eva_1_app_movil.utils.TilValidator
import com.example.eva_1_app_movil.utils.showDatePickerDialog
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

class UpdateClient : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_client)

        val clientId = intent.getSerializableExtra("client") as Client2?
        val tilUserName = findViewById<TextInputLayout>(R.id.update_client_activity_til_userName)
        val tilEmail = findViewById<TextInputLayout>(R.id.update_client_activity_til_email)
        val tilPassword = findViewById<TextInputLayout>(R.id.update_client_activity_til_password)
        val btnUpdate = findViewById<Button>(R.id.update_client_activity_btn_update)
        val spnPlanType = findViewById<Spinner>(R.id.update_client_activity_spn_planType)
        val tilPlanStart = findViewById<TextInputLayout>(R.id.update_client_activity_til_planStart)

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


        btnUpdate.setOnClickListener {
            val email = tilEmail.editText?.text.toString()
            val userName = tilUserName.editText?.text.toString()
            val password = tilPassword.editText?.text.toString()
            val planType = spnPlanType.selectedItem.toString()
            val planStart = tilPlanStart.editText?.text.toString()

            val emailValid = TilValidator(tilEmail)
                .email()
                .isValid()

            val userNameValid = TilValidator(tilUserName)
                .isValid()

            val passwordValid = TilValidator(tilPassword)
                .isValid()

            val planStartValid = TilValidator(tilPlanStart)
                .isValid()

            // Toast para revisar que el Spinner esta funcionando
            //Toast.makeText(this, plan, Toast.LENGTH_SHORT).show()

            if (emailValid && userNameValid && passwordValid && planStartValid){
                val client = Client2(
                    id = clientId?.id,
                    userName = userName,
                    email = email,
                    password = password,
                    planType = planType,
                    planStart = SimpleDateFormat("yyyy-MM-dd").parse(planStart)
                )

                ClientsController(this,).update(client)
            }
        }
    }
}