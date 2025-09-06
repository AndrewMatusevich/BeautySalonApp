package com.example.beautysalonapp.di.modules

import android.content.Context
import com.example.beautysalon.data_impl.storages.AuthDataStore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Provides
    @Singleton
    fun providesAuthDataStore(context: Context): AuthDataStore {
        return AuthDataStore(context)
    }
}