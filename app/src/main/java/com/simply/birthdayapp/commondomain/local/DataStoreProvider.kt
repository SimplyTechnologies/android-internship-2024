package com.simply.birthdayapp.commondomain.local

import kotlinx.coroutines.flow.Flow

interface DataStoreProvider {
    fun isSignedIn(): Flow<Boolean>
    suspend fun setSignedIn(isSignedIn: Boolean)
}