package com.example.beautysalon.features.master_impl.api

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.beautysalon.features.auth_impl.presentation.MasterScreen
import com.example.beautysalon.features.master_api.MasterFeatureApi
import javax.inject.Inject

class MasterFeatureApiImpl @Inject constructor() : MasterFeatureApi {
    override val route: String = "masterRole"

    override fun registerDestination(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.composable(route = route){
            MasterScreen()
        }
    }
}