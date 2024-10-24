package com.simply.birthdayapp.main.home.data.repository

import com.simply.birthdayapp.main.home.data.getMockBirthdays
import com.simply.birthdayapp.main.home.domain.repository.BirthdayRepository
import kotlinx.coroutines.flow.flowOf

class BirthdayRepositoryImpl : BirthdayRepository {
    // TODO: replace flow of to api call
    override fun getBirthdays() = flowOf(getMockBirthdays())
}