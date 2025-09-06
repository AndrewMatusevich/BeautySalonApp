package com.example.beautysalonapp.di.modules

import com.example.beautysalon.data.repositories.UserRepository
import com.example.beautysalon.domain_impl.usecases.DeleteUserUseCase
import com.example.beautysalon.domain_impl.usecases.GetUserUseCase
import dagger.Module
import dagger.Provides

@Module()
class UserModule {
    @Provides
    fun ppovideGetUserUseCase(userRepository: UserRepository): GetUserUseCase {
        return GetUserUseCase(userRepository = userRepository)
    }

    @Provides
    fun provideDeleteUserUseCase(userRepository: UserRepository): DeleteUserUseCase {
        return DeleteUserUseCase(userRepository = userRepository)
    }

}