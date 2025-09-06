package com.example.beautysalonapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.beautysalon.features.admin_api.AdminFeatureApi
import com.example.beautysalon.features.auth_api.AuthFeatureApi
import com.example.beautysalon.features.client_api.api.ClientFeatureApi
import com.example.beautysalon.features.master_api.MasterFeatureApi
import com.example.beautysalonapp.di.AppDiProvider.appComponent
import com.example.beautysalonapp.navigation.AppNavHost
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var authFeature: AuthFeatureApi
    @Inject
    lateinit var adminFeatureApi: AdminFeatureApi
    @Inject
    lateinit var clientFeatureApi: ClientFeatureApi
    @Inject
    lateinit var masterFeatureApi: MasterFeatureApi


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        enableEdgeToEdge()
        setContent {
            AppNavHost(
                authFeature = authFeature,
                adminFeature = adminFeatureApi,
                clientFeatureApi = clientFeatureApi,
                masterFeatureApi = masterFeatureApi
            )
        }
    }
}
