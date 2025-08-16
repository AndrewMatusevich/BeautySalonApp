package com.example.beautysalon.features.admin_impl.di

import dagger.Component

@Component(
    modules = [AdminFeatureModule::class],
    dependencies = [AdminFeatureDeps::class]
        )
interface AdminFeatureComponent {
}