package com.simply.birthdayapp.auth.registration.domain.repository

import com.simply.birthdayapp.auth.registration.domain.model.SignUpInputDomainModel
import com.simply.birthdayapp.auth.registration.domain.model.UserDomainModel
import com.simply.birthdayapp.core.result.Result

interface SignUpRepository {
    suspend fun signUp(signUpInput: SignUpInputDomainModel) : Result<UserDomainModel>
}