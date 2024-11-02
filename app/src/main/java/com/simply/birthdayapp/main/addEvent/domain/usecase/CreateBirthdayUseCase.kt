package com.simply.birthdayapp.main.addEvent.domain.usecase

import com.simply.birthdayapp.commondomain.model.Birthday
import com.simply.birthdayapp.core.result.Result
import com.simply.birthdayapp.main.addEvent.domain.model.CreateBirthdayInputDomain
import com.simply.birthdayapp.main.addEvent.domain.repository.BirthDayRepository

interface CreateBirthdayUseCase {
    suspend fun invoke(input: CreateBirthdayInputDomain): Result<Birthday>
}

class CreateBirthdayUseCaseImpl(private val repository: BirthDayRepository): CreateBirthdayUseCase {

    override suspend fun invoke(input: CreateBirthdayInputDomain): Result<Birthday> {
        return repository.createBirthday(input)
    }
}