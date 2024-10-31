package com.simply.birthdayapp.commondomain.di

import com.simply.birthdayapp.commondomain.usecase.IsUserLoggedUseCase
import com.simply.birthdayapp.commondomain.usecase.IsUserLoggedUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val commonDomainModule = module {
    factoryOf(::IsUserLoggedUseCaseImpl) { bind<IsUserLoggedUseCase>() }
}