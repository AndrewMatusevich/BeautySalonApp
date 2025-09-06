package com.example.beautysalon.data_impl.repositories

import com.example.beautysalon.data.repositories.UserRepository
import com.example.beautysalon.data_impl.storages.AuthDataStore
import com.example.beautysalon.domain_models.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor (private val authDataStore: AuthDataStore): UserRepository {

    override suspend fun saveUser(user: User) = authDataStore.saveUserAuthData(user = user)

    override suspend fun readUser() = authDataStore.userAuthData

    override suspend fun deleteUser() = authDataStore.clear()

}