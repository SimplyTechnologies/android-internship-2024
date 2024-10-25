package com.simply.birthdayapp.commondomain.local

import kotlinx.coroutines.flow.Flow

interface DataStoreProvider {
    fun isSignedIn(): Flow<Boolean>
    suspend fun setSignedIn(isSignedIn: Boolean)
    suspend fun saveAccessToken(token: String)
    suspend fun getToken() : Flow<String>
}