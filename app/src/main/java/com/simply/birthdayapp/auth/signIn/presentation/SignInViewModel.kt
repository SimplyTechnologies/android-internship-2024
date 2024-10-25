package com.simply.birthdayapp.auth.signIn.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply.birthdayapp.auth.signIn.domain.model.LoginInput
import com.simply.birthdayapp.auth.signIn.domain.usecase.SaveAccessTokenUseCase
import com.simply.birthdayapp.auth.signIn.domain.usecase.SetSignedInUseCase
import com.simply.birthdayapp.auth.signIn.domain.usecase.SignInUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.simply.birthdayapp.core.result.Result

class SignInViewModel(
    private val setSignedInUseCase: SetSignedInUseCase,
    private val loginUseCase: SignInUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<SignInUiState>(SignInUiState.Idle)
    val uiState: StateFlow<SignInUiState> = _uiState

    fun setSignedIn(isSignedIn: Boolean) {
        viewModelScope.launch {
            setSignedInUseCase.invoke(isSignedIn)
        }
    }

    fun saveAccessTokenUseCase(token: String) {
        viewModelScope.launch {
            saveAccessTokenUseCase.invoke(token)
        }
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = SignInUiState.Loading
            val loginInput = LoginInput(email, password)
            val result = loginUseCase.invoke(loginInput)
            _uiState.value = when (result) {
                is Result.Success -> SignInUiState.Success
                is Result.Error -> SignInUiState.Error(result.message)
                is Result.Loading -> SignInUiState.Loading
            }
        }
    }
}