package com.simply.birthdayapp.auth.signIn.domain.repository
import com.simply.birthdayapp.auth.signIn.domain.model.LoginInput
import com.simply.birthdayapp.core.result.Result

interface SignInRepository {
    suspend fun setSignedIn(isSignedIn: Boolean)
    suspend fun login(loginInput: LoginInput): Result<String>
    suspend fun saveAccessToken(token: String)
}