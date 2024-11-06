package com.simply.birthdayapp.main.addEvent.presentation.di

import com.simply.birthdayapp.main.addEvent.presentation.AddEventViewModel
import com.simply.birthdayapp.main.navigation.BirthdayMode
import com.simply.birthdayapp.main.profile.editprofile.presentation.EditMyProfileViewModel
import com.simply.birthdayapp.main.profile.profile.domain.model.UserDomain
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val addEventPresentationModule = module {
    viewModelOf(::AddEventViewModel)
    viewModel { (model: BirthdayMode) ->
        AddEventViewModel(
            birthdayMode = model, createBirthdayUseCase = get(), imageEncodeUseCase = get(), updateBirthdayUseCase = get(), deleteBirthdayUseCase = get()
        )
    }

}