package com.example.beautysalon.core.navigation

import androidx.navigation.NavHostController

fun NavHostController.navigateToSingleTopTo(route: String){
    navigate(route = route){
        popUpTo(this@navigateToSingleTopTo.graph.id){
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}