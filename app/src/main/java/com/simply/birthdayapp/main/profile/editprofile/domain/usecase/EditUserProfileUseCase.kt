package com.simply.birthdayapp.main.profile.editprofile.domain.usecase

import com.simply.birthdayapp.core.result.Result
import com.simply.birthdayapp.main.profile.editprofile.domain.model.UpdateProfileInput
import com.simply.birthdayapp.main.profile.editprofile.domain.repository.EditUserProfileRepository
import com.simply.birthdayapp.main.profile.profile.domain.model.UserDomain
import kotlinx.coroutines.flow.Flow

interface EditUserProfileUseCase {
    fun invoke(updateProfileInput: UpdateProfileInput): Flow<Result<UserDomain>>
}

class EditUserProfileUseCaseImpl(private val repository: EditUserProfileRepository) :
    EditUserProfileUseCase {
    override fun invoke(updateProfileInput: UpdateProfileInput) =
        repository.updateProfile(updateProfileInput)
}