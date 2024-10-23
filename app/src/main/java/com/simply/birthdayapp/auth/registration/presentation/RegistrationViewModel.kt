package com.simply.birthdayapp.auth.registration.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class RegistrationViewModel : ViewModel() {
    var name = mutableStateOf("")
    var surname = mutableStateOf("")
    var email = mutableStateOf("")
    var password = mutableStateOf("")
    var repeatedPassword = mutableStateOf("")

    var nameError = mutableStateOf<String?>(null)
    var surnameError = mutableStateOf<String?>(null)
    var emailError = mutableStateOf<String?>(null)
    var passwordError = mutableStateOf<String?>(null)
    var repeatedPasswordError = mutableStateOf<String?>(null)

    var isRegisterEnabled = mutableStateOf(false)

    fun validateName() {
        nameError.value = when {
            name.value.isEmpty() -> "First name cannot be empty"
            !name.value.matches(Regex("^[a-zA-Z]{1,20}$")) -> "First name can only contain letters (max 20 characters)"
            else -> null
        }
        validateRegisterButtonState()
    }

    fun validateSurname() {
        surnameError.value = when {
            surname.value.isEmpty() -> "Last name cannot be empty"
            !surname.value.matches(Regex("^[a-zA-Z]{1,20}$")) -> "Last name can only contain letters (max 20 characters)"
            else -> null
        }
        validateRegisterButtonState()
    }

    fun validateEmail() {
        emailError.value = when {
            email.value.isEmpty() -> "Email cannot be empty"
            !email.value.matches(Regex("^[\\w\\.-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$")) -> "Invalid email format"
            else -> null
        }
        validateRegisterButtonState()
    }

    fun validatePassword() {
        passwordError.value = when {
            password.value.isEmpty() -> "Password cannot be empty"
            password.value.length < 8 -> "Password must be at least 8 characters"
            !password.value.contains(Regex("[^a-zA-Z0-9]")) -> "Password must contain at least one special character"
            else -> null
        }
        validateRepeatedPassword()
        validateRegisterButtonState() // Ensure the button state is validated
    }

    fun validateRepeatedPassword() {
        repeatedPasswordError.value = when {
            repeatedPassword.value.isEmpty() -> "Please confirm your password"
            password.value != repeatedPassword.value -> "Passwords do not match"
            else -> null
        }
        validateRegisterButtonState()
    }

    fun validateRegisterButtonState() {
        isRegisterEnabled.value =
            (nameError.value == null) && (surnameError.value == null) && (emailError.value == null) && (passwordError.value == null) && (repeatedPasswordError.value == null) && name.value.isNotEmpty() && surname.value.isNotEmpty() && email.value.isNotEmpty() && password.value.isNotEmpty() && repeatedPassword.value.isNotEmpty()
    }

    fun clearRepeatedPassword() {
        repeatedPassword.value = ""
    }

    fun clearFields() {
        nameError.value = null
        surnameError.value = null
        emailError.value = null
        passwordError.value = null
        repeatedPasswordError.value = null

        name.value = ""
        surname.value = ""
        email.value = ""
        password.value = ""
        repeatedPassword.value = ""
    }
}
