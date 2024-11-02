package com.simply.birthdayapp.main.addEvent.presentation.di

import com.simply.birthdayapp.main.addEvent.presentation.AddEventViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val addEventPresentationModule = module {
    viewModelOf(::AddEventViewModel)
}