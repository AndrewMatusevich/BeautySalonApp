package com.example.beautysalon.data_impl.storages

import androidx.datastore.preferences.core.stringPreferencesKey


object AuthPreferences {
    val PHONE_NUMBER = stringPreferencesKey("phone_number")
    val ROLE = stringPreferencesKey("role")
    val TOKEN = stringPreferencesKey("token")
}