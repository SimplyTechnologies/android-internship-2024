package com.simply.birthdayapp.auth.signIn.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.simply.birthdayapp.auth.signIn.domain.repository.SignInRepository
import com.simply.birthdayapp.commondata.Util.IS_SIGNED_IN

class SignInRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : SignInRepository {
    override suspend fun setSignedIn(isSignedIn: Boolean) {
        dataStore.edit { settings ->
            settings[IS_SIGNED_IN] = isSignedIn
        }
    }
}