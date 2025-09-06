package com.example.beautysalon.features.auth_impl.api

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.beautysalon.features.auth_api.AuthFeatureApi
import com.example.beautysalon.features.auth_impl.navigation.AuthEntry
import com.example.beautysalon.features.auth_impl.presentation.AuthViewModelFactory
import javax.inject.Inject

class AuthFeatureApiImpl @Inject constructor(
    private val viewModelFactory: AuthViewModelFactory
) : AuthFeatureApi {
    override val route: String = "authRole"

    override fun registerDestination(navGraphBuilder: NavGraphBuilder, navigateTo: (String) -> Unit) {
        navGraphBuilder.composable(route = route){
            AuthEntry(navigateTo = navigateTo, viewModelFactory = viewModelFactory)
        }
    }
}