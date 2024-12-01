package com.bootcamp.ejercicio1app.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import com.bootcamp.ejercicio1app.view.HomeView
import com.bootcamp.ejercicio1app.view.NuevoContact
import com.bootcamp.ejercicio1app.viewmodel.ContactViewModel

sealed class Screen(val route: String) {
    object HomeListContacts: Screen("home_list_Contacts")
    object AddContact: Screen("Create_Contact")
}

@Composable
fun NavController(contactViewModel: ContactViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.HomeListContacts.route) {
        composable(Screen.HomeListContacts.route) {
            HomeView(navController, contactViewModel)
        }

        composable(Screen.AddContact.route + "/{id}", arguments = listOf(
            navArgument("id") {
                type = NavType.LongType
                defaultValue = 0L
                nullable = false
            }
        )) { entry ->
            val id = if (entry.arguments != null) entry.arguments!!.getLong("id") else 0L
            NuevoContact(id, navController, contactViewModel)
        }
    }
}