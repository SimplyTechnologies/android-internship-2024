package com.simply.birthdayapp.auth.signIn.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply.birthdayapp.auth.signIn.domain.model.LoginInputDomain
import com.simply.birthdayapp.auth.signIn.domain.usecase.SaveAccessTokenUseCase
import com.simply.birthdayapp.auth.signIn.domain.usecase.SetAuthInitialScreenStateUseCase
import com.simply.birthdayapp.auth.signIn.domain.usecase.SignInUseCase
import com.simply.birthdayapp.core.result.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignInViewModel(
    private val setAuthInitialScreenStateUseCase: SetAuthInitialScreenStateUseCase,
    private val loginUseCase: SignInUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase
) : ViewModel() {
    init {
        setLandingShowed()
    }

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

    private val _uiState = MutableStateFlow<SignInUiState?>(null)
    val uiState: StateFlow<SignInUiState?> = _uiState


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

    private fun setLandingShowed() {
        viewModelScope.launch {
            setAuthInitialScreenStateUseCase.invoke(true)
        }
    }

    private fun saveAccessTokenUseCase(token: String) {
        viewModelScope.launch {
            saveAccessTokenUseCase.invoke(token)
        }
    }

    fun signIn() {
        viewModelScope.launch {
            _uiState.value = SignInUiState.Loading
            val loginInput = LoginInputDomain(_emailText.value, _passwordText.value)
            val result = loginUseCase.invoke(loginInput)
            _uiState.value = when (result) {
                is Result.Success -> {
                    saveAccessTokenUseCase(result.data)
                    SignInUiState.Success(message = result.data)
                }

                is Result.Error -> SignInUiState.Error(result.message)
                is Result.Loading -> SignInUiState.Loading
            }
        }
    }

    fun resetState() {
        _uiState.value = null
    }
}