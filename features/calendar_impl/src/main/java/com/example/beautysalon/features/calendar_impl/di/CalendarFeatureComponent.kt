package com.example.beautysalon.features.calendar_impl.di

import dagger.Component

@Component(
    modules = [CalendarFeatureModule::class],
    dependencies = [CalendarFeatureDeps::class]
        )
interface CalendarFeatureComponent {
}