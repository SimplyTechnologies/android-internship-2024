package com.simply.birthdayapp.auth.authnavigation.data.repository


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.simply.birthdayapp.auth.authnavigation.domain.repository.AuthRepository
import com.simply.birthdayapp.commondata.Util.IS_SIGNED_IN
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class AuthRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
) : AuthRepository {
    override fun isSignedIn(): Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[IS_SIGNED_IN] ?: false
    }.flowOn(Dispatchers.IO)
}