package com.simply.birthdayapp.commondata.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.simply.birthdayapp.commondata.Util.IS_SIGNED_IN
import com.simply.birthdayapp.commondomain.local.ClearTokenDataStoreProvider
import com.simply.birthdayapp.commondomain.local.DataStoreProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map


class DataStoreProviderImpl(
   val  dataStore: DataStore<Preferences>,
) : DataStoreProvider, ClearTokenDataStoreProvider {



    override fun isSignedIn(): Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[IS_SIGNED_IN] ?: false
    }.flowOn(Dispatchers.IO)

    override suspend fun setSignedIn(isSignedIn: Boolean) {
        dataStore.edit { settings ->
            settings[IS_SIGNED_IN] = isSignedIn
        }
    }

    override suspend fun saveAccessToken(token: String) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN_KEY] = token
        }
    }

    override fun getToken(): Flow<String> = dataStore.data.map { preferences ->
        preferences[ACCESS_TOKEN_KEY] ?: ""
    }.flowOn(Dispatchers.IO)

    override suspend fun clearToken() {
        dataStore.edit { preferences ->
            preferences.remove(ACCESS_TOKEN_KEY)
        }
    }

    companion object {
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
    }
}