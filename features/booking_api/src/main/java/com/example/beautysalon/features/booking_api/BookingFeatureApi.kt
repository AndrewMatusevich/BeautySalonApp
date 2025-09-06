package com.example.beautysalon.features.booking_api

import androidx.navigation.NavGraphBuilder
import com.example.beautysalon.core.navigation.FeatureApi

interface BookingFeatureApi: FeatureApi {
    fun registerDestination(navGraphBuilder: NavGraphBuilder)
}