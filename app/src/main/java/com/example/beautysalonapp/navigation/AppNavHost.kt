package com.example.beautysalonapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.beautysalon.features.admin_api.AdminFeatureApi
import com.example.beautysalon.features.auth_api.AuthFeatureApi
import com.example.beautysalon.features.client_api.api.ClientFeatureApi
import com.example.beautysalon.features.master_api.MasterFeatureApi

@Composable
fun AppNavHost(
    authFeature: AuthFeatureApi,
    adminFeature: AdminFeatureApi,
    clientFeatureApi: ClientFeatureApi,
    masterFeatureApi: MasterFeatureApi
) {
    val navController: NavHostController = rememberNavController()

     NavHost(navController, startDestination = authFeature.route) {
         authFeature.registerDestination(
             navGraphBuilder = this,
             navigateTo = { route -> navController.navigate(route)}
         )
         adminFeature.registerDestination(navGraphBuilder = this)
         clientFeatureApi.registerDestination(navGraphBuilder = this)
         masterFeatureApi.registerDestination(navGraphBuilder = this)
     }
}