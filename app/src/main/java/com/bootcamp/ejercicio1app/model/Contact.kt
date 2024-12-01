package com.bootcamp.ejercicio1app.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val telefono: String,
    val correo: String,
    val imagenPerfil: String? = null, // URL para la imagen de perfil
    val fechaNacimiento: String
)