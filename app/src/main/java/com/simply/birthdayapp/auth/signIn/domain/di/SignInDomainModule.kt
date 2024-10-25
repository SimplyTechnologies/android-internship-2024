package com.simply.birthdayapp.auth.signIn.domain.di

import com.simply.birthdayapp.auth.signIn.domain.usecase.SaveAccessTokenUseCase
import com.simply.birthdayapp.auth.signIn.domain.usecase.SaveAccessTokenUseCaseImpl
import com.simply.birthdayapp.auth.signIn.domain.usecase.SetSignedInUseCase
import com.simply.birthdayapp.auth.signIn.domain.usecase.SetSignedInUseCaseImpl
import com.simply.birthdayapp.auth.signIn.domain.usecase.SignInUseCase
import com.simply.birthdayapp.auth.signIn.domain.usecase.SignInUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val signInDomainModule = module {
    factoryOf(::SetSignedInUseCaseImpl) { bind<SetSignedInUseCase>() }
    factoryOf(::SignInUseCaseImpl) {bind<SignInUseCase>()}
    factoryOf(::SaveAccessTokenUseCaseImpl) {bind<SaveAccessTokenUseCase>()}
}