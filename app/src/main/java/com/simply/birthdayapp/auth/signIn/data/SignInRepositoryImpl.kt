package com.simply.birthdayapp.auth.signIn.data

import com.simply.birthdayapp.auth.signIn.domain.repository.SignInRepository
import com.simply.birthdayapp.commondomain.local.DataStoreProvider

class SignInRepositoryImpl(
    private val dataStoreProvider: DataStoreProvider,
) : SignInRepository {
    override suspend fun setSignedIn(isSignedIn: Boolean) =
        dataStoreProvider.setSignedIn(isSignedIn)
}