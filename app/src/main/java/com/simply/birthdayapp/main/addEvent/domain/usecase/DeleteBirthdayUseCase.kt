package com.simply.birthdayapp.main.addEvent.domain.usecase

import com.simply.birthdayapp.commondomain.model.Birthday
import com.simply.birthdayapp.core.result.Result
import com.simply.birthdayapp.main.addEvent.domain.repository.BirthDayRepository
import kotlinx.coroutines.flow.Flow

interface DeleteBirthdayUseCase {
    fun invoke(id: Int) : Flow<Result<Birthday>>
}

class DeleteBirthdayUseCaseImpl(private val repository: BirthDayRepository) : DeleteBirthdayUseCase {
    override fun invoke(id: Int): Flow<Result<Birthday>> {
        return repository.deleteBirthday(id)
    }
}