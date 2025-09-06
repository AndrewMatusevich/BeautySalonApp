package com.example.beautysalon.features.auth_impl.domain

import com.example.beautysalon.data.repositories.UserRepository
import com.example.beautysalon.domain_models.User
import javax.inject.Inject

class SaveUserUseCase @Inject constructor (private val repository: UserRepository) {
    suspend fun execute(user: User) = repository.saveUser(user)
}