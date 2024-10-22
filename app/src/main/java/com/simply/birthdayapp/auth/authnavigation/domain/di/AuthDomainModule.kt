package com.simply.birthdayapp.auth.authnavigation.domain.di

import com.simply.birthdayapp.auth.authnavigation.domain.usecase.IsSignedInUseCase
import com.simply.birthdayapp.auth.authnavigation.domain.usecase.IsSignedInUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val authDomainModule = module {
    factoryOf(::IsSignedInUseCaseImpl) { bind<IsSignedInUseCase>() }
}