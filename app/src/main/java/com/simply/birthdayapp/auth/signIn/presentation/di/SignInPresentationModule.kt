package com.simply.birthdayapp.auth.signIn.presentation.di

import com.simply.birthdayapp.auth.signIn.presentation.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val signInPresentationModule = module {
    viewModelOf(::SignInViewModel)
}