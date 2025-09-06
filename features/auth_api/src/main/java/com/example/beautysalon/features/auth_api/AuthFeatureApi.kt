package com.example.beautysalon.features.auth_api

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.beautysalon.core.navigation.FeatureApi

interface AuthFeatureApi: FeatureApi {
    fun registerDestination(navGraphBuilder: NavGraphBuilder, navigateTo: (String) -> Unit)
}