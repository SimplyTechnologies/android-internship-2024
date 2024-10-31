package com.simply.birthdayapp.main.profile.editprofile.presentation.di

import com.simply.birthdayapp.main.profile.editprofile.presentation.EditMyProfileViewModel
import com.simply.birthdayapp.main.profile.profile.domain.model.UserDomain
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val editMyProfilePresentationModule = module {
    viewModel { (model: UserDomain) ->
        EditMyProfileViewModel(
            user = model,
            editUserProfileUseCase = get(),
            imageEncoderDecoderRepository = get()
        )
    }
}