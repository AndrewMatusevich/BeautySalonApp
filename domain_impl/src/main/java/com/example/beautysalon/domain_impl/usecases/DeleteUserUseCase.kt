package com.example.beautysalon.domain_impl.usecases

import com.example.beautysalon.data.repositories.UserRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor (private val userRepository: UserRepository) {
    suspend fun execute() = userRepository.deleteUser()
}