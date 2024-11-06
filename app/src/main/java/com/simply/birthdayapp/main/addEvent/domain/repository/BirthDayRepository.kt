package com.simply.birthdayapp.main.addEvent.domain.repository

import com.simply.birthdayapp.commondomain.model.Birthday
import com.simply.birthdayapp.core.result.Result
import com.simply.birthdayapp.main.addEvent.domain.model.CreateBirthdayInputDomain
import kotlinx.coroutines.flow.Flow

interface BirthDayRepository {
    fun createBirthday(input: CreateBirthdayInputDomain): Flow<Result<Birthday>>
    fun updateBirthday(id: Int, input: CreateBirthdayInputDomain): Flow<Result<Birthday>>
    fun deleteBirthday(id: Int) : Flow<Result<Birthday>>
}