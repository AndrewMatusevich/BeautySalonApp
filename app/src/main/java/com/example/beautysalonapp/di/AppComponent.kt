package com.example.beautysalonapp.di

import android.content.Context
import com.example.beautysalon.features.admin_impl.di.AdminFeatureDeps
import com.example.beautysalon.features.auth_impl.di.AuthFeatureDeps
import com.example.beautysalon.features.booking_impl.di.BookingFeatureDeps
import com.example.beautysalon.features.calendar_impl.di.CalendarFeatureDeps
import com.example.beautysalon.features.master_impl.di.MasterFeatureDeps
import com.example.beautysalon.features.profile_impl.di.ProfileFeatureDeps
import dagger.BindsInstance
import dagger.Component


@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        FirebaseModule::class,
        RepositoryModule::class,
        FeatureModule::class])
interface AppComponent : AdminFeatureDeps,
                        AuthFeatureDeps,
                        BookingFeatureDeps,
                        CalendarFeatureDeps,
                        MasterFeatureDeps,
                        ProfileFeatureDeps {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun addContext(context: Context): Builder
        fun build(): AppComponent
    }
}