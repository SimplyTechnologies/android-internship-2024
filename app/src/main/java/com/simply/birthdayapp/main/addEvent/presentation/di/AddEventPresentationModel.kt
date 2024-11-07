package com.simply.birthdayapp.main.addEvent.presentation.di

import com.simply.birthdayapp.main.addEvent.presentation.AddEventViewModel
import com.simply.birthdayapp.main.navigation.BirthdayMode
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addEventPresentationModule = module {
    viewModel { (model: BirthdayMode) ->
        AddEventViewModel(
            birthdayMode = model,
            createBirthdayUseCase = get(),
            imageEncodeUseCase = get(),
            updateBirthdayUseCase = get(),
            deleteBirthdayUseCase = get()
        )
    }
}