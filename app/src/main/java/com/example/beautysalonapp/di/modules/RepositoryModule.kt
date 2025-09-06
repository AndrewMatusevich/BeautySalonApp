package com.example.beautysalonapp.di.modules

import com.example.beautysalon.data.repositories.UserRepository
import com.example.beautysalon.data_impl.repositories.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

}