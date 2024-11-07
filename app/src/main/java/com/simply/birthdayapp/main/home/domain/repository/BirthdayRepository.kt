package com.simply.birthdayapp.main.home.domain.repository

import com.simply.birthdayapp.commondomain.model.Birthday
import com.simply.birthdayapp.core.result.Result
import kotlinx.coroutines.flow.Flow

interface BirthdayRepository {
    fun getBirthdays(): Flow<Result<List<Birthday>>>
}