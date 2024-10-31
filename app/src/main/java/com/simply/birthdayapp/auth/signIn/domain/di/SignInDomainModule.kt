package com.simply.birthdayapp.auth.signIn.domain.di

import com.simply.birthdayapp.auth.signIn.domain.usecase.SaveAccessTokenUseCase
import com.simply.birthdayapp.auth.signIn.domain.usecase.SaveAccessTokenUseCaseImpl
import com.simply.birthdayapp.auth.signIn.domain.usecase.SetAuthInitialScreenStateUseCase
import com.simply.birthdayapp.auth.signIn.domain.usecase.SetAuthInitialScreenStateUseCaseImpl
import com.simply.birthdayapp.auth.signIn.domain.usecase.SignInUseCase
import com.simply.birthdayapp.auth.signIn.domain.usecase.SignInUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val signInDomainModule = module {
    factoryOf(::SetAuthInitialScreenStateUseCaseImpl) { bind<SetAuthInitialScreenStateUseCase>() }
    factoryOf(::SignInUseCaseImpl) {bind<SignInUseCase>()}
    factoryOf(::SaveAccessTokenUseCaseImpl) {bind<SaveAccessTokenUseCase>()}
}