package com.simply.birthdayapp.main.addEvent.domain.repository

import com.simply.birthdayapp.commondomain.model.Birthday
import com.simply.birthdayapp.main.addEvent.domain.model.CreateBirthdayInputDomain
import com.simply.birthdayapp.core.result.Result

interface BirthDayRepository {
    suspend fun createBirthday(input: CreateBirthdayInputDomain): Result<Birthday>
}