package com.example.eva_1_app_movil.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.eva_1_app_movil.R
import com.example.eva_1_app_movil.models.Client2

class ClientsAdapter(ctx: Context, clients: List<Client2>): BaseAdapter() {
    val ctx = ctx
    val clients = clients

    override fun getCount(): Int {
        return clients.size
    }

    override fun getItem(position: Int): Client2 {
        return clients[position]
    }

    override fun getItemId(position: Int): Long {
        return clients[position].id!!
    }

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        val inflater = LayoutInflater.from(ctx)
        val rowView = inflater.inflate(R.layout.item_client, null)

        val client = clients[position]
        val tvClient = rowView.findViewById<TextView>(R.id.item_client_tv_title)

        tvClient.text = "${position + 1}) ${client.userName}"

        return rowView
    }
}