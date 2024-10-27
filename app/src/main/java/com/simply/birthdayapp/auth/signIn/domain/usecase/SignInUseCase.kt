package com.simply.birthdayapp.auth.signIn.domain.usecase

import com.simply.birthdayapp.auth.signIn.domain.model.LoginInputDomain
import com.simply.birthdayapp.auth.signIn.domain.repository.SignInRepository
import com.simply.birthdayapp.core.result.Result

interface SignInUseCase {
    suspend fun invoke(loginInput: LoginInputDomain) : Result<String>
}

class SignInUseCaseImpl(private val repository : SignInRepository) : SignInUseCase {

    override suspend fun invoke(loginInput: LoginInputDomain): Result<String> {
        return repository.login(loginInput)
    }
}

