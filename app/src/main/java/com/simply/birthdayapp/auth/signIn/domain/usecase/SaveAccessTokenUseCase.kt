package com.simply.birthdayapp.auth.signIn.domain.usecase

import com.simply.birthdayapp.auth.signIn.domain.repository.SignInRepository

interface SaveAccessTokenUseCase {
    suspend fun invoke(token: String)
}

class SaveAccessTokenUseCaseImpl(private val repository: SignInRepository) : SaveAccessTokenUseCase {

    override suspend fun invoke(token: String) {
        repository.saveAccessToken(token)
    }
}