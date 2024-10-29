package com.simply.birthdayapp.main.shop.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import com.simply.birthdayapp.main.shop.presentation.ShopViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module

val shopPresentationModule = module {
    viewModel { ShopViewModel(get()) }
}