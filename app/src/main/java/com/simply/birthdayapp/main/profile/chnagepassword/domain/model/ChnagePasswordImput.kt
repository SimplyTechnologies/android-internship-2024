package com.simply.birthdayapp.main.profile.chnagepassword.domain.model

data class ChangePasswordInput(
    var newPassword: String,
    var oldPassword: String,
)