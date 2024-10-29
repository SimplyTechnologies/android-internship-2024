package com.simply.birthdayapp.main.profile.profile.domain.usecase

import com.simply.birthdayapp.core.result.Result
import com.simply.birthdayapp.main.profile.profile.domain.model.UserDomain
import com.simply.birthdayapp.main.profile.profile.domain.repository.UserProfileRepository
import kotlinx.coroutines.flow.Flow

interface GetUserProfileUseCase {
    fun invoke(): Flow<Result<UserDomain>>
}

class GetUserProfileUseCaseImpl(private val userProfileRepository: UserProfileRepository) :
    GetUserProfileUseCase {
    override fun invoke() = userProfileRepository.getUserProfile()
}