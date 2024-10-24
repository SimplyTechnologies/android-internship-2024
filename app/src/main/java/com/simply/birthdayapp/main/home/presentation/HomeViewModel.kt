package com.simply.birthdayapp.main.home.presentation

import androidx.lifecycle.ViewModel
import com.simply.birthdayapp.main.home.domain.usecase.GetBirthdayUseCase

class HomeViewModel(
    private val birthdayUseCase: GetBirthdayUseCase
) : ViewModel() {
    val birthdays = birthdayUseCase.invoke()

}