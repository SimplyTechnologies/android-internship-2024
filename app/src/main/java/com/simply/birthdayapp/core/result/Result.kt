package com.simply.birthdayapp.core.result

sealed class Result<out T> {
    data class Loading<T>(val message: String): Result<T>()
    data class Success<T>(val data: T) : Result<T>()
    data class Error<T>(val message: String) : Result<T>()
}