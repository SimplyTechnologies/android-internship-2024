package com.simply.birthdayapp.commondata.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.simply.birthdayapp.commondata.Util.IS_SIGNED_IN
import com.simply.birthdayapp.commondomain.local.DataStoreProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map


class DataStoreProviderImpl(
    context: Context,
) : DataStoreProvider {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "isSignedIn")
    private val dataStore = context.dataStore

    override fun isSignedIn(): Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[IS_SIGNED_IN] ?: false
    }.flowOn(Dispatchers.IO)

    override suspend fun setSignedIn(isSignedIn: Boolean) {
        dataStore.edit { settings ->
            settings[IS_SIGNED_IN] = isSignedIn
        }
    }
}