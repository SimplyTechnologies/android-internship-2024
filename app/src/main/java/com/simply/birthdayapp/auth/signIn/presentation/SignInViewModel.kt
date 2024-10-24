package com.simply.birthdayapp.auth.signIn.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply.birthdayapp.auth.signIn.domain.usecase.SetSignedInUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignInViewModel(
    private val setSignedInUseCase: SetSignedInUseCase,
) : ViewModel() {

    private val _emailText = MutableStateFlow("")
    val emailText: StateFlow<String> = _emailText.asStateFlow()

    private val _passwordText = MutableStateFlow("")
    val passwordText: StateFlow<String> = _passwordText.asStateFlow()


    private val _emailError = MutableStateFlow<String?>("")
    val emailError: StateFlow<String?> = _emailError.asStateFlow()

    private val _passwordError = MutableStateFlow<String?>("")
    val passwordError: StateFlow<String?> = _passwordError.asStateFlow()

    private val _isSignInButtonEnable = mutableStateOf(false)
    val isSignInButtonEnable = _isSignInButtonEnable


    fun setEmailText(newValue: String) {
        _emailText.value = newValue
        validateSignInButtonState()
    }

    fun setPasswordText(newValue: String) {
        _passwordText.value = newValue
        validateSignInButtonState()
    }

    private fun validateSignInButtonState() {
        _isSignInButtonEnable.value =
            _emailText.value.isNotEmpty() && _passwordText.value.isNotEmpty()
    }

    fun setSignedIn(isSignedIn: Boolean) {
        viewModelScope.launch {
            setSignedInUseCase.invoke(isSignedIn)
        }
    }
}