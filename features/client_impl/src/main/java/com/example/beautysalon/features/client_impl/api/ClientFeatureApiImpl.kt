package com.example.beautysalon.features.client_impl.api

import javax.inject.Inject
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.beautysalon.features.client_api.api.ClientFeatureApi
import com.example.beautysalon.features.client_impl.presentation.ClientScreen


class ClientFeatureApiImpl @Inject constructor(): ClientFeatureApi {

    override val route: String = "Client"
    override fun registerDestination(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.composable(route = route) {
            ClientScreen()
        }
    }
}