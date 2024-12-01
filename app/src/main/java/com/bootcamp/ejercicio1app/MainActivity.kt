package com.bootcamp.ejercicio1app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import dagger.hilt.android.AndroidEntryPoint
import com.bootcamp.ejercicio1app.ui.theme.Ejercicio1AppTheme
import com.bootcamp.ejercicio1app.viewmodel.ContactViewModel


@AndroidEntryPoint


class MainActivity : ComponentActivity() {
    private val contactViewModel: ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Ejercicio1AppTheme {


                }
            }
        }
    }
