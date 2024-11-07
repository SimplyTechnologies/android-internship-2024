package com.simply.birthdayapp.main.home.presentation.di

import com.simply.birthdayapp.commondomain.model.Birthday
import com.simply.birthdayapp.main.home.presentation.BirthdayDetailsViewModel
import com.simply.birthdayapp.main.home.presentation.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val homePresentationModule = module {
    viewModelOf(::HomeViewModel)

    viewModel { (model: Birthday) ->
        BirthdayDetailsViewModel(
            birthday = model,
            context = androidContext()
        )
    }
}