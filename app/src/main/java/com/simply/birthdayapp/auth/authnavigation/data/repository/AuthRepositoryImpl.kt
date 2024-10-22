package com.simply.birthdayapp.auth.authnavigation.data.repository


import com.simply.birthdayapp.auth.authnavigation.domain.repository.AuthRepository
import com.simply.birthdayapp.commondomain.local.DataStoreProvider
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(
    private val dataStoreProvider: DataStoreProvider,
) : AuthRepository {
    override fun isSignedIn(): Flow<Boolean> = dataStoreProvider.isSignedIn()
}