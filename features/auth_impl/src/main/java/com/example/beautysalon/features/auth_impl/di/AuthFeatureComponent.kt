package com.example.beautysalon.features.auth_impl.di

import dagger.Component

@Component(
    modules = [AuthFeatureModule::class,
              ViewModelModule::class],
    dependencies = [AuthFeatureDeps::class]
        )
interface AuthFeatureComponent {
}