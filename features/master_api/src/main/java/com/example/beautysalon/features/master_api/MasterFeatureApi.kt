package com.example.beautysalon.features.master_api

import androidx.navigation.NavGraphBuilder
import com.example.beautysalon.core.navigation.FeatureApi

interface MasterFeatureApi: FeatureApi {
    fun registerDestination(navGraphBuilder: NavGraphBuilder)
}