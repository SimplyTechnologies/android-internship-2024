package com.simply.birthdayapp.main.home.domain.di

import com.simply.birthdayapp.main.home.domain.usecase.GetBirthdaysUseCase
import com.simply.birthdayapp.main.home.domain.usecase.GetBirthdaysUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val homeDomainModule = module {
    factoryOf(::GetBirthdaysUseCaseImpl) { bind<GetBirthdaysUseCase>() }
}