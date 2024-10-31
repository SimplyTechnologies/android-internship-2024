package com.simply.birthdayapp.main.profile.editprofile.domain.repository

import com.simply.birthdayapp.core.result.Result
import com.simply.birthdayapp.main.profile.editprofile.domain.model.UpdateProfileInput
import com.simply.birthdayapp.main.profile.profile.domain.model.UserDomain
import kotlinx.coroutines.flow.Flow

interface EditUserProfileRepository {
    fun updateProfile(updateProfileInput: UpdateProfileInput): Flow<Result<UserDomain>>
}
