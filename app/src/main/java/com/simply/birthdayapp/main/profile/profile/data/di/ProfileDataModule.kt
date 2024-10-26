package com.simply.birthdayapp.main.profile.profile.data.di

import com.simply.birthdayapp.main.profile.profile.data.repository.UserProfileRepositoryImpl
import com.simply.birthdayapp.main.profile.profile.domain.repository.UserProfileRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val profileDataModule = module {
    singleOf(::UserProfileRepositoryImpl) { bind<UserProfileRepository>() }
}