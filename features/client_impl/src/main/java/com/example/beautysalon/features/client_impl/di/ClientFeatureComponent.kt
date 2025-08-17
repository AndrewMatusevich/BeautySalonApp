package com.example.beautysalon.features.client_impl.di

import dagger.Component

@Component(
    modules = [ClientFeatureModule::class],
    dependencies = [ClientFeatureDeps::class]
        )
interface ClientFeatureComponent {
}