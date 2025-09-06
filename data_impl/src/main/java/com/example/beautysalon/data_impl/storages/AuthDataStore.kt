package com.example.beautysalon.data_impl.storages

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.beautysalon.domain_models.User
import com.example.beautysalon.domain_models.enums.Role
import javax.inject.Inject

// Расширение для контекста
val Context.authDataStore by preferencesDataStore("auth_prefs")

class AuthDataStore @Inject constructor (private val context: Context) {

    companion object {
        private val PHONE_KEY = stringPreferencesKey("phone")
        private val ROLE_KEY = stringPreferencesKey("role")
        private val TOKEN_KEY = stringPreferencesKey("token")
    }

    // Сохранение
    suspend fun saveUserAuthData(user: User) {
        context.authDataStore.edit { prefs ->
            prefs[PHONE_KEY] = user.phone
            prefs[ROLE_KEY] = user.role.name   // сохраняем имя enum-а (CLIENT, MASTER, ADMIN)
            user.token?.let { prefs[TOKEN_KEY] = it }
        }
    }

    // Чтение
    val userAuthData: Flow<User?> = context.authDataStore.data.map { prefs ->
        val phone = prefs[PHONE_KEY]
        val roleStr = prefs[ROLE_KEY]
        val token = prefs[TOKEN_KEY]

        if (phone != null && roleStr != null) {
            val role = try {
                Role.valueOf(roleStr) // enum conversion
            } catch (e: Exception) {
                Role.CLIENT // fallback
            }
            User(token = token, phone = phone, role = role)
        } else {
            null
        }
    }

    // Очистка (logout)
    suspend fun clear() {
        context.authDataStore.edit { it.clear() }
    }
}

