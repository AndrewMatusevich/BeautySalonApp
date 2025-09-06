package com.example.beautysalon.features.booking_impl.api

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.beautysalon.features.auth_impl.presentation.BookingScreen
import com.example.beautysalon.features.booking_api.BookingFeatureApi
import javax.inject.Inject

class BookingFeatureApiImpl @Inject constructor() : BookingFeatureApi {
    override val route: String = "bookingRole"

    override fun registerDestination(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.composable(route = route){
        BookingScreen()
        }
    }
}