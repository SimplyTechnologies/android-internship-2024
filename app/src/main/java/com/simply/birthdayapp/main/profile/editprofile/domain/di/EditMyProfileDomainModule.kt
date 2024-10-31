package com.simply.birthdayapp.main.profile.editprofile.domain.di

import com.simply.birthdayapp.main.profile.editprofile.domain.usecase.EditUserProfileUseCase
import com.simply.birthdayapp.main.profile.editprofile.domain.usecase.EditUserProfileUseCaseImpl
import com.simply.birthdayapp.main.profile.editprofile.presentation.EditMyProfileViewModel
import com.simply.birthdayapp.main.profile.profile.domain.model.UserDomain
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val editMyProfileDomainModule = module {
    factory<EditUserProfileUseCase> { EditUserProfileUseCaseImpl(get()) }
}