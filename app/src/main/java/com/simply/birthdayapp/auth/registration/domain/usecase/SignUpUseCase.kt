package com.simply.birthdayapp.auth.registration.domain.usecase

import com.simply.birthdayapp.auth.registration.domain.model.SignUpInputDomainModel
import com.simply.birthdayapp.auth.registration.domain.model.UserDomainModel
import com.simply.birthdayapp.auth.registration.domain.repository.SignUpRepository
import com.simply.birthdayapp.core.result.Result

interface SignUpUseCase {
    suspend fun invoke(signUpInput: SignUpInputDomainModel) : Result<UserDomainModel>
}

class SignUpUseCaseImpl(private val signUpRepository: SignUpRepository) : SignUpUseCase {

    override suspend fun invoke(signUpInput: SignUpInputDomainModel): Result<UserDomainModel> {
        return signUpRepository.signUp(signUpInput)
    }
}