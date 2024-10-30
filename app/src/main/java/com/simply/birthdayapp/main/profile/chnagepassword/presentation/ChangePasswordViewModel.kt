package com.simply.birthdayapp.main.profile.chnagepassword.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply.birthdayapp.R
import com.simply.birthdayapp.core.result.Result
import com.simply.birthdayapp.main.profile.chnagepassword.domain.model.ChangePasswordInput
import com.simply.birthdayapp.main.profile.chnagepassword.domain.usecase.ChangePasswordUseCase
import com.simply.birthdayapp.main.profile.chnagepassword.domain.usecase.ClearAccessTokenUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChangePasswordViewModel(
    private val changePasswordUseCase: ChangePasswordUseCase,
    private val clearAccessTokenUseCase: ClearAccessTokenUseCase
) : ViewModel() {

    private val _oldPassword = MutableStateFlow("")
    val oldPassword: StateFlow<String> = _oldPassword.asStateFlow()

    private val _newPassword = MutableStateFlow("")
    val newPassword: StateFlow<String> = _newPassword.asStateFlow()

    private val _newPasswordError = MutableStateFlow<Int?>(null)
    val newPasswordError: StateFlow<Int?> = _newPasswordError.asStateFlow()

    private val _repeatNewPassword = MutableStateFlow("")
    val repeatNewPassword: StateFlow<String> = _repeatNewPassword.asStateFlow()

    private val _repeatNewPasswordError = MutableStateFlow<Int?>(null)
    val repeatNewPasswordError: StateFlow<Int?> = _repeatNewPasswordError.asStateFlow()

    private val _isDoneEnabled = MutableStateFlow(false)
    val isDoneEnabled: StateFlow<Boolean> = _isDoneEnabled.asStateFlow()

    private val _uiState = MutableStateFlow<ChangePasswordUiState?>(null)
    val uiState: StateFlow<ChangePasswordUiState?> = _uiState.asStateFlow()

    private val _isNavigationToLoginScreen = MutableSharedFlow<Boolean>()
    val isNavigationToLoginScreen = _isNavigationToLoginScreen.asSharedFlow()

    fun setOldPassword(newValue: String) {
        viewModelScope.launch { _oldPassword.emit(newValue) }
        checkValidateButtonState()
    }

    fun setNewPassword(newValue: String) {
        viewModelScope.launch { _newPassword.emit(newValue) }
        validateNewPassword()
    }

    fun setRepeatNewPassword(newValue: String) {
        viewModelScope.launch { _repeatNewPassword.emit(newValue) }
        validateNewRepeatedPassword()
    }

    private fun validateNewPassword() {
        viewModelScope.launch {
            val passwordRegex = Regex("^(?=.*[a-z])(?=.*[!\"#$%&'()*+,-./:;<=>?@^_`{|}~])[A-Za-z\\d!\"#$%&'()*+,-./:;<=>?@^_`{|}~]{8,20}")

            _newPasswordError.emit(
                when {
                    _newPassword.value.isEmpty() -> R.string.error_empty_password
                    !passwordRegex.matches(_newPassword.value) -> R.string.error_password_characters
                    else -> null
                }
            )
        }
        validateNewRepeatedPassword()
        checkValidateButtonState()
    }

    private fun validateNewRepeatedPassword() {
        viewModelScope.launch {
            _repeatNewPasswordError.emit(
                when {
                    _repeatNewPassword.value.isEmpty() -> R.string.error_empty_password
                    _newPassword.value != _repeatNewPassword.value -> R.string.error_passwords_not_match
                    else -> null
                }
            )
        }
        checkValidateButtonState()
    }


    private fun checkValidateButtonState() {
        val isOldPasswordNotEmpty = _oldPassword.value.isNotEmpty()
        val isNewPasswordNotEmpty = _newPassword.value.isNotEmpty()
        val isRepeatNewPasswordNotEmpty = _repeatNewPassword.value.isNotEmpty()

        val isNewPasswordValid = _newPasswordError.value == null
        val isRepeatPasswordValid = _repeatNewPasswordError.value == null

        _isDoneEnabled.value =
            isOldPasswordNotEmpty && isNewPasswordNotEmpty && isRepeatNewPasswordNotEmpty && isNewPasswordValid && isRepeatPasswordValid
    }

    fun setUiState(uiState: ChangePasswordUiState?) {
        viewModelScope.launch {
            _uiState.emit(uiState)
        }
    }

    fun changePassword() {
        viewModelScope.launch {
            val response = changePasswordUseCase.invoke(
                input = ChangePasswordInput(
                    oldPassword = oldPassword.value,
                    newPassword = newPassword.value,
                )
            )

            response.collect {
                _uiState.emit(
                    when (it) {
                        is Result.Error -> {
                            ChangePasswordUiState.Error(it.message)
                        }

                        is Result.Loading -> {
                            ChangePasswordUiState.Loading
                        }

                        is Result.Success -> {
                            ChangePasswordUiState.Success
                        }
                    }
                )
            }
        }
    }

    fun doSignOut() {
        viewModelScope.launch {
            _isNavigationToLoginScreen.emit(true)
            clearAccessToken()
        }
    }

    private suspend fun clearAccessToken() {
        clearAccessTokenUseCase.invoke()
    }
}
