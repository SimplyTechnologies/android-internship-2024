package com.simply.birthdayapp.auth.signIn.domain.usecase

import com.simply.birthdayapp.auth.signIn.domain.repository.SignInRepository

interface SetAuthInitialScreenStateUseCase {
    suspend fun invoke(isSignedIn: Boolean)
}

class SetAuthInitialScreenStateUseCaseImpl(private val repository: SignInRepository) :
    SetAuthInitialScreenStateUseCase {
    override suspend fun invoke(isSignedIn: Boolean) = repository.setSignedIn(isSignedIn)
}