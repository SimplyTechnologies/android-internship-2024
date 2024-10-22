package com.simply.birthdayapp.auth.signIn.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply.birthdayapp.auth.signIn.domain.usecase.SetSignedInUseCase
import kotlinx.coroutines.launch

class SignInViewModel(
    private val setSignedInUseCase: SetSignedInUseCase,
) : ViewModel() {

    fun setSignedIn(isSignedIn: Boolean) {
        viewModelScope.launch {
            setSignedInUseCase.invoke(isSignedIn)
        }
    }
}