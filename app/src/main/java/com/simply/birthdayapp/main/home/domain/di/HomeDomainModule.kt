package com.simply.birthdayapp.main.home.domain.di

import com.simply.birthdayapp.main.home.domain.usecase.GetBirthdayUseCase
import com.simply.birthdayapp.main.home.domain.usecase.GetBirthdayUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val homeDomainModule = module {
    factoryOf(::GetBirthdayUseCaseImpl) { bind<GetBirthdayUseCase>() }
}