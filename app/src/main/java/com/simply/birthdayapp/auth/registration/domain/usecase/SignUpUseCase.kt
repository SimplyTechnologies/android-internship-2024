package com.simply.birthdayapp.auth.registration.domain.usecase

import com.simply.birthdayapp.auth.registration.domain.model.SignUpInput
import com.simply.birthdayapp.auth.registration.domain.model.User
import com.simply.birthdayapp.auth.registration.domain.repository.SignUpRepository
import com.simply.birthdayapp.core.result.Result

interface SignUpUseCase {
    suspend fun invoke(signUpInput: SignUpInput) : Result<User>
}

class SignUpUseCaseImpl(private val signUpRepository: SignUpRepository) : SignUpUseCase {

    override suspend fun invoke(signUpInput: SignUpInput): Result<User> {
        return signUpRepository.signUp(signUpInput)
    }
}