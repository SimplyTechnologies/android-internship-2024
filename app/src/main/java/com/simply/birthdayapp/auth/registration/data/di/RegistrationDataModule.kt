package com.simply.birthdayapp.auth.registration.data.di

import com.simply.birthdayapp.auth.registration.data.repository.SignUpRepositoryImpl
import com.simply.birthdayapp.auth.registration.domain.repository.SignUpRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val registrationDataModule = module {
    singleOf(::SignUpRepositoryImpl) {bind<SignUpRepository>()}
}