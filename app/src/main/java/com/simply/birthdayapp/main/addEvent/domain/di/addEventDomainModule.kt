package com.simply.birthdayapp.main.addEvent.domain.di

import com.simply.birthdayapp.main.addEvent.domain.usecase.CreateBirthdayUseCase
import com.simply.birthdayapp.main.addEvent.domain.usecase.CreateBirthdayUseCaseImpl
import com.simply.birthdayapp.main.addEvent.domain.usecase.DeleteBirthdayUseCase
import com.simply.birthdayapp.main.addEvent.domain.usecase.DeleteBirthdayUseCaseImpl
import com.simply.birthdayapp.main.addEvent.domain.usecase.UpdateBirthdayUseCase
import com.simply.birthdayapp.main.addEvent.domain.usecase.UpdateBirthdayUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val addEventDomainModule = module {
    factoryOf(::CreateBirthdayUseCaseImpl) {bind<CreateBirthdayUseCase>()}
    factoryOf(::UpdateBirthdayUseCaseImpl) {bind<UpdateBirthdayUseCase>()}
    factoryOf(::DeleteBirthdayUseCaseImpl) {bind<DeleteBirthdayUseCase>()}
}

