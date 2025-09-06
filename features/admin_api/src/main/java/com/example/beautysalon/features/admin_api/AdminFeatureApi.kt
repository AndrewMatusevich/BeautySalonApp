package com.example.beautysalon.features.admin_api

import androidx.navigation.NavGraphBuilder
import com.example.beautysalon.core.navigation.FeatureApi

interface AdminFeatureApi: FeatureApi {
    fun registerDestination(navGraphBuilder: NavGraphBuilder)
}