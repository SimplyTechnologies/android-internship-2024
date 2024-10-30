package com.simply.birthdayapp.main.profile.chnagepassword.data.di

import com.simply.birthdayapp.main.profile.chnagepassword.data.repository.ChangePasswordRepositoryImpl
import com.simply.birthdayapp.main.profile.chnagepassword.domain.repository.ChangePasswordRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val changePasswordDataModule = module {
    singleOf(::ChangePasswordRepositoryImpl) { bind<ChangePasswordRepository>() }
}