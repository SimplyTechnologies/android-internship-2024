package com.simply.birthdayapp.auth.signIn.domain.usecase

import com.simply.birthdayapp.auth.signIn.domain.repository.SignInRepository

interface SetSignedInUseCase {
    suspend fun invoke(isSignedIn: Boolean)
}

class SetSignedInUseCaseImpl(private val repository: SignInRepository) : SetSignedInUseCase {
    override suspend fun invoke(isSignedIn: Boolean) =
        repository.setSignedIn(isSignedIn)
}