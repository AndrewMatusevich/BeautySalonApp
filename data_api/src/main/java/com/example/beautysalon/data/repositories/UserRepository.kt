package com.example.beautysalon.data.repositories

import com.example.beautysalon.domain_models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun saveUser(user: User)
    suspend fun readUser(): Flow<User?>
    suspend fun deleteUser()
}