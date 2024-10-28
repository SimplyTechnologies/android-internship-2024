package com.simply.birthdayapp.auth.registration.data.mapper

import com.simply.birthdayapp.auth.registration.data.model.SignUpInputDataModel
import com.simply.birthdayapp.auth.registration.domain.model.SignUpInputDomainModel

fun SignUpInputDomainModel.toSignUpDataModel() = SignUpInputDataModel(
    firstName = this.firstName,
    lastName = this.lastName,
    email = this.email,
    password = this.password
)

fun SignUpInputDataModel.toSignUpDomainModel() = SignUpInputDomainModel(
    firstName = this.firstName,
    lastName = this.lastName,
    email = this.email,
    password = this.password
)