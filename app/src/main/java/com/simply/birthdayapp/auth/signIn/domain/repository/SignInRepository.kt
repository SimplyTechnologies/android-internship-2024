package com.simply.birthdayapp.auth.signIn.domain.repository

interface SignInRepository {
    suspend fun setSignedIn(isSignedIn: Boolean)
}