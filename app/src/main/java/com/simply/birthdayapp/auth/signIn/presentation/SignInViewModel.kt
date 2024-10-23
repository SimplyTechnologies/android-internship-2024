package com.simply.birthdayapp.auth.signIn.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply.birthdayapp.auth.signIn.domain.usecase.SetSignedInUseCase
import kotlinx.coroutines.launch

class SignInViewModel(
    private val setSignedInUseCase: SetSignedInUseCase,
) : ViewModel() {

    val emailText = mutableStateOf("")
    val passwordText = mutableStateOf("")
    val emailError = mutableStateOf<String?>("")
    val passwordError = mutableStateOf<String?>("")

    fun setSignedIn(isSignedIn: Boolean) {
        viewModelScope.launch {
            setSignedInUseCase.invoke(isSignedIn)
        }
    }
}