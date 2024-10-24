package com.simply.birthdayapp.main.home.domain.usecase

import com.simply.birthdayapp.commondomain.model.Birthday
import com.simply.birthdayapp.main.home.domain.repository.BirthdayRepository
import kotlinx.coroutines.flow.Flow

interface GetBirthdaysUseCase {
    fun invoke(): Flow<List<Birthday>>
}

class GetBirthdaysUseCaseImpl(val repository: BirthdayRepository) : GetBirthdaysUseCase {
    override fun invoke() = repository.getBirthdays()
}