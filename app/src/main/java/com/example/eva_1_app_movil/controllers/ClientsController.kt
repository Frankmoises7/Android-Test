package com.example.eva_1_app_movil.controllers

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.room.Room
import com.example.eva_1_app_movil.ClientsActivity
import com.example.eva_1_app_movil.lib.AppDatabase
import com.example.eva_1_app_movil.lib.BCrypt
import com.example.eva_1_app_movil.models.Client2
import com.example.eva_1_app_movil.models.ClientEntity
import java.lang.Exception

class ClientsController(ctx: Context){
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
        )}

        return clients
    }

    /* ________________ INFO FALSEADA ___________________

        fun getAll () {
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
        return clients
        ________________ INFO FALSEADA ___________________  */


    /* ________________ FUNCIONES EN CASO DE NECESITARLAS ___________________


    fun findById(id: Long): Client2? {
        val entity = dao.findById(id) ?: return null

        return Client2(
            id = entity.id,
            userName = entity.userName,
            email = entity.email,
            password = entity.password,
            planStart = entity.planStart,
            planType = entity.planType
            )
    }

    fun findByEmail(email: String): Client2? {
        val entity = dao.findByEmail(email) ?: return null

        return Client2(
            id = entity.id,
            userName = entity.userName,
            email = entity.email,
            password = entity.password,
            planStart = entity.planStart,
            planType = entity.planType
        )
    }

 ________________ FUNCIONES EN CASO DE NECESITARLAS ___________________ */

    fun create(client: Client2){
        val hashedPassword = BCrypt.hashpw(client.password, BCrypt.gensalt())
        val clientEntity = ClientEntity(
            id = null,
            userName = client.userName,
            email = client.email,
            password = hashedPassword,
            planStart = client.planStart,
            planType = client.planType
        )

        val db = Room.databaseBuilder(
            ctx,
            AppDatabase::class.java, "IronBoxFitness-app3"
        )
            .allowMainThreadQueries()
            .build()

        val dao = db.clientDao()

        try {
            dao.insert(clientEntity)
            Toast.makeText(this.ctx, "Nuevo cliente '${client.userName}' Registrado", Toast.LENGTH_SHORT).show()
            val intent = Intent(this.ctx, ClientsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            this.ctx.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this.ctx, "Cuenta existente", Toast.LENGTH_SHORT).show()
        }
    }

    fun update(client: Client2) {

        val hashedPassword = BCrypt.hashpw(client.password, BCrypt.gensalt())
        val clientEntity = ClientEntity(
            id = client.id,
            userName = client.userName,
            email = client.email,
            password = hashedPassword,
            planStart = client.planStart,
            planType = client.planType
        )
        dao.update(clientEntity)
        Toast.makeText(this.ctx, "Cliente actualizado", Toast.LENGTH_SHORT).show()
        val intent = Intent(this.ctx, ClientsActivity::class.java)
        this.ctx.startActivity(intent)
    }

    fun delete(id: Long) {
        dao.delete(id)
        Toast.makeText(this.ctx, "Cliente eliminado", Toast.LENGTH_SHORT).show()
        val intent = Intent(this.ctx, ClientsActivity::class.java)
        this.ctx.startActivity(intent)
    }
}
