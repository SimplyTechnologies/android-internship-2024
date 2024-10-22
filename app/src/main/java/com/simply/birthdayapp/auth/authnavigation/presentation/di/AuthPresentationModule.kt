package com.simply.birthdayapp.auth.authnavigation.presentation.di

import com.simply.birthdayapp.auth.authnavigation.presentation.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val authPresentationModule = module {
    viewModelOf(::AuthViewModel)
}