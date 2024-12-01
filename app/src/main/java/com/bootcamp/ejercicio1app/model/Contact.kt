package com.bootcamp.ejercicio1app.model

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nombre: String,
    val telefono: String,
    val correo: String,
    val imagenPerfil: String, // URL para la imagen de perfil
    val fechaNacimiento: String
)