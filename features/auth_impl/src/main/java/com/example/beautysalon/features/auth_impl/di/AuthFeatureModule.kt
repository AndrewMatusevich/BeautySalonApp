package com.example.beautysalon.features.auth_impl.di

import com.example.beautysalon.data.repositories.UserRepository
import com.example.beautysalon.features.auth_impl.domain.SaveUserUseCase
import dagger.Module
import dagger.Provides

@Module
class AuthFeatureModule {
    @Provides
    fun providesSaveUserUseCase(repository: UserRepository): SaveUserUseCase {
        return SaveUserUseCase(repository = repository)
    }
}