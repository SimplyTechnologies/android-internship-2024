package com.simply.birthdayapp.auth.signIn.domain.usecase

import com.simply.birthdayapp.auth.signIn.domain.model.LoginInput
import com.simply.birthdayapp.auth.signIn.domain.repository.SignInRepository
import com.simply.birthdayapp.core.result.Result

interface SignInUseCase {
    suspend fun invoke(loginInput: LoginInput) : Result<String>
}

class SignInUseCaseImpl(private val repository : SignInRepository) : SignInUseCase {

    override suspend fun invoke(loginInput: LoginInput): Result<String> {
        return repository.login(loginInput)
    }
}

