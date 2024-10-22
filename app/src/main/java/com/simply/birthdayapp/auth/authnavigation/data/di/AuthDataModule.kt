package com.simply.birthdayapp.auth.authnavigation.data.di

import com.simply.birthdayapp.auth.authnavigation.data.repository.AuthRepositoryImpl
import com.simply.birthdayapp.auth.authnavigation.domain.repository.AuthRepository
import org.koin.dsl.module

var authDataModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
}