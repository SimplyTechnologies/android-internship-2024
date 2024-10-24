package com.simply.birthdayapp.auth.registration.data.di

import com.simply.birthdayapp.auth.registration.data.repository.SignUpRepositoryImpl
import com.simply.birthdayapp.auth.registration.domain.repository.SignUpRepository
import org.koin.dsl.module

val registrationDataModule = module {
    single<SignUpRepository> {
        SignUpRepositoryImpl(apolloClient = get())
    }
}