package com.simply.birthdayapp.auth.registration.domain.repository

import com.simply.birthdayapp.auth.registration.domain.model.SignUpInput
import com.simply.birthdayapp.auth.registration.domain.model.User
import com.simply.birthdayapp.core.result.Result

interface SignUpRepository {
    suspend fun signUp(signUpInput: SignUpInput) : Result<User>
}