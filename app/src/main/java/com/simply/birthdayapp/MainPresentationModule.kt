package com.simply.birthdayapp

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val mainPresentationModule = module {
    viewModelOf(::MainViewModel)
}