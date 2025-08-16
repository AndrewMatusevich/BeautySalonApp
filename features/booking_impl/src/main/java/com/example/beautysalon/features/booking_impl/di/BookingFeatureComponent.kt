package com.example.beautysalon.features.booking_impl.di

import dagger.Component

@Component(
    modules = [BookingFeatureModule::class],
    dependencies = [BookingFeatureDeps::class]
        )
interface BookingFeatureComponent {
}