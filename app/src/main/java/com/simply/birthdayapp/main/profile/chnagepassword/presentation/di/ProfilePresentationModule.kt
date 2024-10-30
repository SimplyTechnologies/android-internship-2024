package com.simply.birthdayapp.main.profile.chnagepassword.presentation.di

import com.simply.birthdayapp.main.profile.chnagepassword.presentation.ChangePasswordViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val changePasswordPresentationModule = module {
    viewModelOf(::ChangePasswordViewModel)
}