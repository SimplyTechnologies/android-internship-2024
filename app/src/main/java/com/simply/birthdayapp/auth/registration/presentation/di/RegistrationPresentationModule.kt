package com.simply.birthdayapp.auth.registration.presentation.di

import com.simply.birthdayapp.auth.registration.presentation.RegistrationViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val registrationPresentationModule = module {
    viewModelOf(::RegistrationViewModel)
}