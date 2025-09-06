package com.example.beautysalon.features.auth_impl.di

import com.example.beautysalon.data.repositories.UserRepository
import com.example.beautysalon.domain_impl.usecases.DeleteUserUseCase
import com.example.beautysalon.domain_impl.usecases.GetUserUseCase

interface AuthFeatureDeps {
    val userRepository: UserRepository
    val getUserUseCase: GetUserUseCase
    val deleteUserUseCase: DeleteUserUseCase
}