package com.simply.birthdayapp.main.profile.profile.presentation.di

import com.simply.birthdayapp.main.profile.profile.presentation.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val profilePresentationModule = module {
    viewModelOf(::ProfileViewModel)
}