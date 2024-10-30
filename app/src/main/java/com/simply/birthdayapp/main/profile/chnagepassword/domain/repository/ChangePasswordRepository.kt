package com.simply.birthdayapp.main.profile.chnagepassword.domain.repository

import com.simply.birthdayapp.core.result.Result
import com.simply.birthdayapp.main.profile.chnagepassword.domain.model.ChangePasswordInput
import kotlinx.coroutines.flow.Flow

interface ChangePasswordRepository {
    fun changePassword(input: ChangePasswordInput): Flow<Result<Boolean>>
}