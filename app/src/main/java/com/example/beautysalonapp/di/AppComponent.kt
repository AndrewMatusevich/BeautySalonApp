package com.example.beautysalonapp.di

import android.content.Context
import com.example.beautysalon.data.repositories.UserRepository
import com.example.beautysalon.domain_impl.usecases.DeleteUserUseCase
import com.example.beautysalon.domain_impl.usecases.GetUserUseCase
import com.example.beautysalon.features.admin_impl.di.AdminFeatureDeps
import com.example.beautysalon.features.auth_impl.di.AuthFeatureDeps
import com.example.beautysalon.features.booking_impl.di.BookingFeatureDeps
import com.example.beautysalon.features.calendar_impl.di.CalendarFeatureDeps
import com.example.beautysalon.features.client_impl.di.ClientFeatureDeps
import com.example.beautysalon.features.master_impl.di.MasterFeatureDeps
import com.example.beautysalon.features.profile_impl.di.ProfileFeatureDeps
import com.example.beautysalonapp.MainActivity
import com.example.beautysalonapp.di.modules.AppModule
import com.example.beautysalonapp.di.modules.FeatureModule
import com.example.beautysalonapp.di.modules.FirebaseModule
import com.example.beautysalonapp.di.modules.NetworkModule
import com.example.beautysalonapp.di.modules.RepositoryModule
import com.example.beautysalonapp.di.modules.StorageModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        FirebaseModule::class,
        StorageModule::class,
        RepositoryModule::class,
        FeatureModule::class
    ]
)
interface AppComponent : AdminFeatureDeps,
                        AuthFeatureDeps,
                        BookingFeatureDeps,
                        CalendarFeatureDeps,
                        ClientFeatureDeps,
                        MasterFeatureDeps,
                        ProfileFeatureDeps
{
    override val userRepository: UserRepository
    override val getUserUseCase: GetUserUseCase
    override val deleteUserUseCase: DeleteUserUseCase

    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun addContext(context: Context): Builder
        fun build(): AppComponent
    }
}