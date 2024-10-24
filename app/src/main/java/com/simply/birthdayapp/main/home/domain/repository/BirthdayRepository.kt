package com.simply.birthdayapp.main.home.domain.repository

import com.simply.birthdayapp.commondomain.model.Birthday
import kotlinx.coroutines.flow.Flow

interface BirthdayRepository {
    fun getBirthdays(): Flow<List<Birthday>>
}