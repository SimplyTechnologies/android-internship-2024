package com.simply.birthdayapp.main.home.data.di

import com.simply.birthdayapp.main.home.data.repository.BirthdayRepositoryImpl
import com.simply.birthdayapp.main.home.domain.repository.BirthdayRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val homeDataModule = module {
    singleOf(::BirthdayRepositoryImpl) { bind<BirthdayRepository>() }
}