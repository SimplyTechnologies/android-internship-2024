package com.simply.birthdayapp.auth.registration.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply.birthdayapp.auth.registration.domain.model.SignUpInput
import com.simply.birthdayapp.auth.registration.domain.usecase.SignUpUseCase
import com.simply.birthdayapp.core.result.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegistrationViewModel(private val signUpUseCase: SignUpUseCase) : ViewModel() {
    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _surname = MutableStateFlow("")
    val surname: StateFlow<String> = _surname.asStateFlow()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _repeatedPassword = MutableStateFlow("")
    val repeatedPassword: StateFlow<String> = _repeatedPassword.asStateFlow()

    private val _nameError = MutableStateFlow<String?>(null)
    val nameError: StateFlow<String?> = _nameError.asStateFlow()

    private val _surnameError = MutableStateFlow<String?>(null)
    val surnameError: StateFlow<String?> = _surnameError.asStateFlow()

    private val _emailError = MutableStateFlow<String?>(null)
    val emailError: StateFlow<String?> = _emailError.asStateFlow()

    private val _passwordError = MutableStateFlow<String?>(null)
    val passwordError: StateFlow<String?> = _passwordError.asStateFlow()

    private val _repeatedPasswordError = MutableStateFlow<String?>(null)
    val repeatedPasswordError: StateFlow<String?> = _repeatedPasswordError.asStateFlow()

    private val _isRegisterEnabled = mutableStateOf(false)
    val isRegisterEnabled: State<Boolean> = _isRegisterEnabled

    private val _uiState = MutableStateFlow<SignUpUiState>(SignUpUiState.Idle)
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    fun setName(newValue: String) {
        _name.value = newValue
        checkValidateButtonState()
    }

    fun setSurname(newValue: String) {
        _surname.value = newValue
        checkValidateButtonState()
    }

    fun setEmail(newValue: String) {
        _email.value = newValue
        checkValidateButtonState()
    }

    fun setPassword(newValue: String) {
        _password.value = newValue
        checkValidateButtonState()
    }

    fun setRepeatedPassword(newValue: String) {
        _repeatedPassword.value = newValue
        checkValidateButtonState()
    }



    fun validateName() {
        viewModelScope.launch {
            _nameError.emit(
                when {
                    name.value.isEmpty() -> "First name cannot be empty"
                    !name.value.matches(Regex("^[a-zA-Z]{1,20}$")) -> "First name can only contain letters (max 20 characters)"
                    else -> null
                }
            )
        }
        checkValidateButtonState()
    }

    fun validateSurname() {
        viewModelScope.launch {
            _surnameError.emit(
                when {
                    _surname.value.isEmpty() -> "Last name cannot be empty"
                    !_surname.value.matches(Regex("^[a-zA-Z]{1,20}$")) -> "Last name can only contain letters (max 20 characters)"
                    else -> null
                }
            )
        }
        checkValidateButtonState()
    }

    fun validateEmail() {
        viewModelScope.launch {
            _emailError.emit(
                when {
                    _email.value.isEmpty() -> "Email cannot be empty"
                    !_email.value.matches(Regex("^[\\w.-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$")) -> "Invalid email format"
                    else -> null
                }
            )
        }
        checkValidateButtonState()
    }

    fun validatePassword() {
        viewModelScope.launch {
            _passwordError.emit(
                when {
                    _password.value.isEmpty() -> "Password cannot be empty"
                    _password.value.length < 8 -> "Password must be at least 8 characters"
                    !_password.value.contains(Regex("[^a-zA-Z0-9]")) -> "Password must contain at least one special character"
                    else -> null
                }
            )
        }
        validateRepeatedPassword()
        checkValidateButtonState()
    }

    fun validateRepeatedPassword() {
        viewModelScope.launch {
            _repeatedPasswordError.emit(
                when {
                    _repeatedPassword.value.isEmpty() -> "Please confirm your password"
                    _repeatedPassword.value.length < 8 -> "Password must be at least 8 characters"
                    _password.value != _repeatedPassword.value -> "Passwords do not match"
                    else -> null
                }
            )
        }
        checkValidateButtonState()
    }

    private fun checkValidateButtonState() {
        _isRegisterEnabled.value =
            (nameError.value == null) && (surnameError.value == null) && (emailError.value == null) && (passwordError.value == null) && (repeatedPasswordError.value == null) && _name.value.isNotEmpty() && _surname.value.isNotEmpty() && _email.value.isNotEmpty() && _password.value.isNotEmpty() && _repeatedPassword.value.isNotEmpty()
    }


    fun signUp(signUpInput: SignUpInput) {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading

            val result = signUpUseCase.invoke(signUpInput)
            _uiState.value = when (result) {
                is Result.Success -> SignUpUiState.Success("Account created successfully!")
                is Result.Error -> {
                    when (result.message) {
                        "User already exists" -> SignUpUiState.UserExists("User already exists.")
                        else -> SignUpUiState.GeneralError("Sorry, an error occurred registering your account, please try again.")
                    }
                }
                is Result.Loading -> {
                    SignUpUiState.Loading
                }
            }
        }
    }

    fun resetState() {
        _uiState.value = SignUpUiState.Idle
    }
}