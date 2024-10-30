package com.simply.birthdayapp.main.shop.domain.di

import com.simply.birthdayapp.main.shop.domain.usecase.GetShopsUseCase
import com.simply.birthdayapp.main.shop.domain.usecase.GetShopsUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val shopDomainModule = module {
    factoryOf(::GetShopsUseCaseImpl) { bind<GetShopsUseCase>() }
}