package com.simply.birthdayapp.main.profile.profile.domain.repository

import com.simply.birthdayapp.core.result.Result
import com.simply.birthdayapp.main.profile.profile.domain.model.UserDomain
import kotlinx.coroutines.flow.Flow

interface UserProfileRepository {
    fun getUserProfile(): Flow<Result<UserDomain>>
}