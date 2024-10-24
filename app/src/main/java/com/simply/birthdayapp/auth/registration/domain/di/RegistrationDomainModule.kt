package com.simply.birthdayapp.auth.registration.domain.di

import com.simply.birthdayapp.auth.registration.domain.usecase.SignUpUseCase
import com.simply.birthdayapp.auth.registration.domain.usecase.SignUpUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val registrationDomainModule = module {
    factoryOf(::SignUpUseCaseImpl) {bind<SignUpUseCase>()}
}