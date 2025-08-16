package com.example.beautysalon.features.master_impl.di

import dagger.Component

@Component(
    modules = [MasterFeatureModule::class],
    dependencies = [MasterFeatureDeps::class]
        )
interface MasterFeatureComponent {
}