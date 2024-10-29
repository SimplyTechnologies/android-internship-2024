package com.simply.birthdayapp.main.shop.data.di

import com.simply.birthdayapp.main.shop.domain.repository.ShopRepository
import com.simply.birthdayapp.main.shop.data.repository.ShopRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val shopDataModule = module {
    singleOf(::ShopRepositoryImpl) { bind<ShopRepository>() }
}