package com.simply.birthdayapp.main.addEvent.domain.usecase

import com.simply.birthdayapp.commondomain.model.Birthday
import com.simply.birthdayapp.core.result.Result
import com.simply.birthdayapp.main.addEvent.domain.model.CreateBirthdayInputDomain
import com.simply.birthdayapp.main.addEvent.domain.repository.BirthDayRepository
import kotlinx.coroutines.flow.Flow

interface CreateBirthdayUseCase {
    fun invoke(input: CreateBirthdayInputDomain): Flow<Result<Birthday>>
}

class CreateBirthdayUseCaseImpl(private val repository: BirthDayRepository) :
    CreateBirthdayUseCase {

    override fun invoke(input: CreateBirthdayInputDomain): Flow<Result<Birthday>> {
        return repository.createBirthday(input)
    }
}