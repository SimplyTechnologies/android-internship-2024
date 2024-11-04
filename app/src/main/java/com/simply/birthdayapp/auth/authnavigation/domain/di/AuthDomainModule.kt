package com.simply.birthdayapp.auth.authnavigation.domain.di

import com.simply.birthdayapp.auth.authnavigation.domain.usecase.GetAuthInitialDestinationUseCase
import com.simply.birthdayapp.auth.authnavigation.domain.usecase.GetAuthInitialDestinationUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val authDomainModule = module {
    factoryOf(::GetAuthInitialDestinationUseCaseImpl) { bind<GetAuthInitialDestinationUseCase>() }
}