package com.example.beautysalon.features.client_api.api

import androidx.navigation.NavGraphBuilder
import com.example.beautysalon.core.navigation.FeatureApi

interface ClientFeatureApi: FeatureApi {
    fun registerDestination(navGraphBuilder: NavGraphBuilder)
}