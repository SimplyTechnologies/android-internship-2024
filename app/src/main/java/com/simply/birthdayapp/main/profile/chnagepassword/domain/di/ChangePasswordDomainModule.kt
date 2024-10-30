package com.simply.birthdayapp.main.profile.chnagepassword.domain.di

import com.simply.birthdayapp.main.profile.chnagepassword.domain.usecase.ChangePasswordUseCase
import com.simply.birthdayapp.main.profile.chnagepassword.domain.usecase.ChangePasswordUseCaseImpl
import com.simply.birthdayapp.main.profile.chnagepassword.domain.usecase.ClearAccessTokenUseCase
import com.simply.birthdayapp.main.profile.chnagepassword.domain.usecase.ClearAccessTokenUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val changePasswordDomainModule = module {
    factoryOf(::ChangePasswordUseCaseImpl) { bind<ChangePasswordUseCase>() }
    factoryOf(::ClearAccessTokenUseCaseImpl) { bind<ClearAccessTokenUseCase>() }
}