package com.example.beautysalonapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.beautysalon.features.admin_impl.presentation.AdminScreen
import com.example.beautysalon.features.auth_impl.presentation.AuthScreen
import com.example.beautysalon.features.auth_impl.presentation.MasterScreen
import com.example.beautysalon.features.client_impl.presentation.ClientScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "auth") {
                composable("auth") { AuthScreen(navController = navController) }
                composable("adminScreen") { AdminScreen() }
                composable("masterScreen") { MasterScreen() }
                composable("clientScreen") { ClientScreen() }
            }
        }
    }
}
