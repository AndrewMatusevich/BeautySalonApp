package com.example.beautysalon.features.admin_impl.api

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.beautysalon.features.admin_api.AdminFeatureApi
import com.example.beautysalon.features.admin_impl.presentation.AdminScreen
import javax.inject.Inject

class AdminFeatureApiImpl @Inject constructor() : AdminFeatureApi {
    override val route: String = "adminRole"

    override fun registerDestination(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.composable(route = route){
            AdminScreen()
        }
    }
}