package com.simply.birthdayapp.auth.authnavigation.domain.usecase

import com.simply.birthdayapp.auth.authnavigation.domain.repository.AuthRepository
import com.simply.birthdayapp.auth.navigation.Destination
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface GetAuthInitialDestinationUseCase {
    fun invoke(): Flow<Destination>
}

class GetAuthInitialDestinationUseCaseImpl(private val repository: AuthRepository) : GetAuthInitialDestinationUseCase {
    override fun invoke() = flow {
        repository.isLandingScreenRequired().collect { isSignedIn ->
            emit(if (isSignedIn) Destination.SignInDestination else Destination.LandingDestination)
        }
    }
}