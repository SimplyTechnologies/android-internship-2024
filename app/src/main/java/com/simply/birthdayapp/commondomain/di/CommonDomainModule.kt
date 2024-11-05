package com.simply.birthdayapp.commondomain.di

import com.simply.birthdayapp.commondomain.usecase.ImageEncodeUseCase
import com.simply.birthdayapp.commondomain.usecase.ImageEncodeUseCaseImpl
import com.simply.birthdayapp.commondomain.usecase.IsUserLoggedInUseCase
import com.simply.birthdayapp.commondomain.usecase.IsUserLoggedInUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val commonDomainModule = module {
    factoryOf(::IsUserLoggedInUseCaseImpl) { bind<IsUserLoggedInUseCase>() }
    factoryOf(::ImageEncodeUseCaseImpl) { bind<ImageEncodeUseCase>() }

}