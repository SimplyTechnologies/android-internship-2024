package com.simply.birthdayapp.auth.signIn.data.di

import com.simply.birthdayapp.auth.signIn.data.SignInRepositoryImpl
import com.simply.birthdayapp.auth.signIn.domain.repository.SignInRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val sigInDataModule = module {
    singleOf(::SignInRepositoryImpl) { bind<SignInRepository>() }
}