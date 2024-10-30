package com.simply.birthdayapp.main.profile.chnagepassword.domain.usecase

import com.simply.birthdayapp.core.result.Result
import com.simply.birthdayapp.main.profile.chnagepassword.domain.model.ChangePasswordInput
import com.simply.birthdayapp.main.profile.chnagepassword.domain.repository.ChangePasswordRepository
import kotlinx.coroutines.flow.Flow

interface ChangePasswordUseCase {
    suspend fun invoke(input: ChangePasswordInput): Flow<Result<Boolean>>
}

class ChangePasswordUseCaseImpl(
    private val changePasswordRepository: ChangePasswordRepository,
) : ChangePasswordUseCase {
    override suspend fun invoke(input: ChangePasswordInput) =
        changePasswordRepository.changePassword(input)
}