package com.simply.birthdayapp.main.addEvent.domain.usecase

import com.simply.birthdayapp.commondomain.model.Birthday
import com.simply.birthdayapp.core.result.Result
import com.simply.birthdayapp.main.addEvent.domain.model.CreateBirthdayInputDomain
import com.simply.birthdayapp.main.addEvent.domain.repository.BirthDayRepository
import kotlinx.coroutines.flow.Flow

interface UpdateBirthdayUseCase {
    fun invoke(id: Int, input: CreateBirthdayInputDomain): Flow<Result<Birthday>>
}

class UpdateBirthdayUseCaseImpl(private val repository: BirthDayRepository) : UpdateBirthdayUseCase {

    override fun invoke(id: Int, input: CreateBirthdayInputDomain): Flow<Result<Birthday>> {
        return repository.updateBirthday(id, input)
    }
}

