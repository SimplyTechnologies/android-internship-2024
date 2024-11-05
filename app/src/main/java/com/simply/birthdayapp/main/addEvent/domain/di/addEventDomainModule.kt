package com.simply.birthdayapp.main.addEvent.domain.di

import com.simply.birthdayapp.main.addEvent.domain.usecase.CreateBirthdayUseCase
import com.simply.birthdayapp.main.addEvent.domain.usecase.CreateBirthdayUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val addEventDomainModule = module {
    factoryOf(::CreateBirthdayUseCaseImpl) {bind<CreateBirthdayUseCase>()}
}

