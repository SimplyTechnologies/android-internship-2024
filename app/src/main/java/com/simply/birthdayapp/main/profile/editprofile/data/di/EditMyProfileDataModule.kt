package com.simply.birthdayapp.main.profile.editprofile.data.di

import com.simply.birthdayapp.main.profile.editprofile.data.repository.EditUserProfileRepositoryImpl
import com.simply.birthdayapp.main.profile.editprofile.domain.repository.EditUserProfileRepository
import org.koin.dsl.module

val editMyProfileDataModule = module {
    factory<EditUserProfileRepository> { EditUserProfileRepositoryImpl(get()) }
}