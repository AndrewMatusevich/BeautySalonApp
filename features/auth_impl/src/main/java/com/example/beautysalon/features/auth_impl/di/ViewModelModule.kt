package com.example.beautysalon.features.auth_impl.di

import com.example.beautysalon.domain_impl.usecases.DeleteUserUseCase
import com.example.beautysalon.domain_impl.usecases.GetUserUseCase
import com.example.beautysalon.features.auth_impl.domain.SaveUserUseCase
import com.example.beautysalon.features.auth_impl.presentation.AuthViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Provides
    @Singleton
    fun provideAuthViewModel(
        saveUserUseCase: SaveUserUseCase,
        getUserUseCase: GetUserUseCase,
        deleteUserUseCase: DeleteUserUseCase
    ): AuthViewModelFactory {
        return AuthViewModelFactory(
            saveUserUseCase = saveUserUseCase,
            getUserUseCase = getUserUseCase,
            deleteUserUseCase = deleteUserUseCase
        )
    }
}