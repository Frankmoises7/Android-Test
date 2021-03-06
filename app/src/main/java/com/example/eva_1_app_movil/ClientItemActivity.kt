package com.example.eva_1_app_movil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.eva_1_app_movil.controllers.ClientsController
import com.example.eva_1_app_movil.dao.clientDAO
import com.example.eva_1_app_movil.lib.AppDatabase
import com.example.eva_1_app_movil.models.Client2
import com.example.eva_1_app_movil.utils.Converters
import java.text.SimpleDateFormat

class ClientItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_item)


        val client = intent.getSerializableExtra("client") as Client2
        val tvTitle = findViewById<TextView>(R.id.client_item_activity_tv_title)
        val btnUpdate = findViewById<Button>(R.id.client_item_activity_btn_update)
        val btnDelete = findViewById<Button>(R.id.client_item_activity_btn_delete)
        val tvPlanType = findViewById<TextView>(R.id.client_item_activity_tv_planType)
        val tvPlanStart = findViewById<TextView>(R.id.client_item_activity_tv_planStart)
        val tvEmail = findViewById<TextView>(R.id.client_item_activity_tv_email)
        val controller = ClientsController(this)

        //Titulo (ID - USERNAME)
        tvTitle.text = "ID ${client.id} - ${client.userName}"

        //Tipo de Plan
        tvPlanType.text = client.planType

        //Formateador de fecha
        val planStart = SimpleDateFormat("dd/MM/yyyy")
        tvPlanStart.text = planStart.format(client.planStart).toString()

        //Email
        tvEmail.text = client.email

        btnUpdate.setOnClickListener {
            val intent = Intent(this, UpdateClient::class.java)
            intent.putExtra("client", client)
            startActivity(intent)
        }

        btnDelete.setOnClickListener {
            controller.delete(client.id!!)
        }
    }
}