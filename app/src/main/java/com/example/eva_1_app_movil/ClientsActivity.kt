package com.example.eva_1_app_movil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.example.eva_1_app_movil.controllers.ClientsController
import com.example.eva_1_app_movil.ui.ClientsAdapter

class ClientsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clients)

        val lvClient = findViewById<ListView>(R.id.clients_activity_lv_clients)
        val allClients = ClientsController(this).getAll()
        val adapter = ClientsAdapter(this, allClients)

        lvClient.adapter = adapter
        lvClient.setOnItemClickListener { adapterView, view, position, id ->
            run{
                val client = allClients[position]
                val intent = Intent(view.context, ClientItemActivity::class.java)
                intent.putExtra("client", client)
                view.context.startActivity(intent)
            }
        }
    }
}