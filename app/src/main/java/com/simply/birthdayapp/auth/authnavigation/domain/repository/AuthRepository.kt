package com.simply.birthdayapp.auth.authnavigation.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun isSignedIn(): Flow<Boolean>
}