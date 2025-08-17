package com.example.beautysalon.core.navigation

import androidx.navigation.NavGraphBuilder

interface FeatureApi {
    val route: String
    fun registerDestination(navGraphBuilder: NavGraphBuilder)
}