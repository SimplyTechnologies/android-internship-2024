package com.simply.birthdayapp.main.profile.profile.domain.di

import com.simply.birthdayapp.main.profile.profile.domain.usecase.GetUserProfileUseCase
import com.simply.birthdayapp.main.profile.profile.domain.usecase.GetUserProfileUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val profileDomainModule = module {
    factoryOf(::GetUserProfileUseCaseImpl) { bind<GetUserProfileUseCase>() }
}