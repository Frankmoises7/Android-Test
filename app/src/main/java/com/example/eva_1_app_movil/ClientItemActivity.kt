package com.example.eva_1_app_movil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.eva_1_app_movil.models.Client

class ClientItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_item)

        val client = intent.getSerializableExtra("client") as Client

        val tvTitle = findViewById<TextView>(R.id.client_item_activity_tv_title)

        tvTitle.text = "ID ${client.id} - ${client.title}"
    }
}