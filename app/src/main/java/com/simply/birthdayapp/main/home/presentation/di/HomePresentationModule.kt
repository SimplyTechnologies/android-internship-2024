package com.simply.birthdayapp.main.home.presentation.di

import com.simply.birthdayapp.main.home.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val homePresentationModule = module {
    viewModelOf(::HomeViewModel)

}