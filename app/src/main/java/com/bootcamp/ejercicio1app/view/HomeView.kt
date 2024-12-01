package com.bootcamp.ejercicio1app.view

import androidx.navigation.NavHostController
import com.bootcamp.ejercicio1app.R

import com.bootcamp.ejercicio1app.navigation.Screen

import com.bootcamp.ejercicio1app.model.Contact
import com.bootcamp.ejercicio1app.room.ContactDao
import com.bootcamp.ejercicio1app.viewmodel.ContactViewModel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import coil.compose.AsyncImage
import com.bootcamp.ejercicio1app.components.AppCompo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeView(navController: NavHostController, contactViewModel: ContactViewModel) {

    val contacts by contactViewModel.contacts.collectAsState(initial = emptyList())
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            AppCompo(title = "Contactos")
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screen.AddContact.route + "/0L") }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar contacto")
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            items(contacts) { contact ->
                ContactCard(
                    contact = contact,
                    navController = navController,
                    contactViewModel = contactViewModel,
                    snackbarHostState = snackbarHostState,
                    scope = scope
                )
            }
        }
    }
}

@Composable
fun ContactCard(
    contact: Contact,
    navController: NavHostController,
    contactViewModel: ContactViewModel,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope
) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text(text = "Eliminar contacto") },
            text = { Text(text = "¿Estás seguro de que deseas eliminar a ${contact.nombre} ?") },
            confirmButton = {
                TextButton(onClick = {
                    contactViewModel.deleteContact(contact)
                    showDeleteDialog = false
                    scope.launch {
                        snackbarHostState.showSnackbar("Contacto eliminado")
                    }
                }) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = contact.imagenPerfil,
                contentDescription = "Imagen de perfil de ${contact.nombre}",
                modifier = Modifier
                    .size(100.dp) // Ajusta el tamaño según tus necesidades
                    .clip(RoundedCornerShape(8.dp)), // Bordes redondeados
                error = painterResource(R.drawable.usuario),
                contentScale = ContentScale.Crop // Ajustar la imagen
            )

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp)
            ) {
                Text(
                    text = "Nombre: ${contact.nombre}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Teléfono: ${contact.telefono}",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Correo: ${contact.correo}",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Fecha de Nacimiento: ${contact.fechaNacimiento}",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(6.dp)
            ) {
                IconButton(onClick = {
                    navController.navigate(Screen.AddContact.route + "/${contact.id}")
                    scope.launch {
                        snackbarHostState.showSnackbar("Editando contacto")
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar",
                        tint = Color.Blue
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                IconButton(onClick = {
                    showDeleteDialog = true
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Borrar",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}