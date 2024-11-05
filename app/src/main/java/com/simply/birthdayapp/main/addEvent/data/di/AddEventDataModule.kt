package com.simply.birthdayapp.main.addEvent.data.di

import com.simply.birthdayapp.main.addEvent.data.repository.BirthdayRepositoryImpl
import com.simply.birthdayapp.main.addEvent.domain.repository.BirthDayRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val addEventDataModule = module {
    singleOf(::BirthdayRepositoryImpl) {bind<BirthDayRepository>()}
}