package com.simply.birthdayapp.main.home.domain.usecase

import com.simply.birthdayapp.commondomain.model.Birthday
import com.simply.birthdayapp.main.home.domain.repository.BirthdayRepository
import kotlinx.coroutines.flow.Flow

interface GetBirthdayUseCase {
    fun invoke(): Flow<List<Birthday>>
}

class GetBirthdayUseCaseImpl(val repository: BirthdayRepository) : GetBirthdayUseCase {
    override fun invoke() = repository.getBirthdays()
}
