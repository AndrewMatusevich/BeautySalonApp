package com.example.beautysalonapp.app

import android.app.Application
import com.example.beautysalon.features.admin_impl.di.AdminFeatureDepsProvider
import com.example.beautysalon.features.auth_impl.di.AuthFeatureDepsProvider
import com.example.beautysalon.features.booking_impl.di.BookingFeatureDepsProvider
import com.example.beautysalon.features.calendar_impl.di.CalendarFeatureDepsProvider
import com.example.beautysalon.features.master_impl.di.MasterFeatureDepsProvider
import com.example.beautysalon.features.profile_impl.di.ProfileFeatureDepsProvider
import com.example.beautysalonapp.di.DaggerAppComponent

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {

        val appComponent = DaggerAppComponent.builder()
            .addContext(this)
            .build()

        AdminFeatureDepsProvider.deps = appComponent
        AuthFeatureDepsProvider.deps = appComponent
        BookingFeatureDepsProvider.deps = appComponent
        CalendarFeatureDepsProvider.deps = appComponent
        MasterFeatureDepsProvider.deps = appComponent
        ProfileFeatureDepsProvider.deps = appComponent
    }
}