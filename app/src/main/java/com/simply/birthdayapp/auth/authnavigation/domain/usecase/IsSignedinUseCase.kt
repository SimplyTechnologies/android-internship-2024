package com.simply.birthdayapp.auth.authnavigation.domain.usecase

import com.simply.birthdayapp.auth.authnavigation.domain.repository.AuthRepository
import com.simply.birthdayapp.auth.navigation.Destination
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface IsSignedInUseCase {
    fun invoke(): Flow<Destination>
}

class IsSignedInUseCaseImpl(private val repository: AuthRepository) : IsSignedInUseCase {
    override fun invoke() = flow {
        repository.isSignedIn().collect { isSignedIn ->
            emit(if (isSignedIn) Destination.SignInDestination else Destination.LandingDestination)
        }
    }
}