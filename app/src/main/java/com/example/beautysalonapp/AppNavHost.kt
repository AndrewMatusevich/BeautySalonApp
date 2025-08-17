package com.example.beautysalonapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
//import com.example.beautysalon.features.auth_impl.presentation.AuthScreen

//@Composable
//fun AppNavHost() {
//    val navController = rememberNavController()
//    NavHost(navController, startDestination = Routes.AUTH) {
//        composable(Routes.AUTH) {
//            AuthScreen(onAuthSuccess = {
//                navController.navigate(Routes.HOME) {
//                    popUpTo(Routes.AUTH) { inclusive = true }
//                }
//            })
//        }
//        composable(Routes.HOME) { HomeScreen() }
//    }
//}