package com.example.beautysalon.features.profile_impl.di

import dagger.Component

@Component(
    modules = [ProfileFeatureModule::class],
    dependencies = [ProfileFeatureDeps::class]
        )
interface ProfileFeatureComponent {
}