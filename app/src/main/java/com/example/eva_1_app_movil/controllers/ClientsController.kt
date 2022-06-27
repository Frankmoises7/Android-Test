package com.example.eva_1_app_movil.controllers

import android.content.Context
import androidx.room.Room
import com.example.eva_1_app_movil.lib.AppDatabase
import com.example.eva_1_app_movil.models.Client2

class ClientsController constructor(ctx: Context){
    val ctx = ctx
    private val dao = Room.databaseBuilder(
        ctx,
        AppDatabase::class.java, "IronBoxFitness-app3"
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
        .clientDao()

        //MOSTRAR TODOS LOS USUARIOS

        fun getAll (): List<Client2> {
            val clientEntities = dao.findAll()

            val clients = ArrayList<Client2>()

            clientEntities.forEach { client -> clients.add(Client2(
                id = client.id,
                email = client.email,
                planType = client.planType,
                planStart = client.planStart,
                userName = client.userName,
                password = client.password
            )
            ) }

            return clients
        }

        /* fun getAll () {
        val clients = ArrayList<Client>()
        var clientsName = arrayOf<String>(
            "Alejandro", "Frank", "Boris", "Roberto", "Matias", "Franco", "Alondra", "Daniela", "Jose", "Pedro", "Maria"
        )
        for (i in clientsName.indices) {
            clients.add(Client(
                id = i.toLong() + 1,
                title = "${clientsName[i]}",
                description = "Descripcion $i"
            ))
        }
        return clients */
}