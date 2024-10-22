package com.simply.birthdayapp.auth.signIn.domain.di

import com.simply.birthdayapp.auth.signIn.domain.usecase.SetSignedInUseCase
import com.simply.birthdayapp.auth.signIn.domain.usecase.SetSignedInUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val signInDomainModule = module {
    factoryOf(::SetSignedInUseCaseImpl) { bind<SetSignedInUseCase>() }
}