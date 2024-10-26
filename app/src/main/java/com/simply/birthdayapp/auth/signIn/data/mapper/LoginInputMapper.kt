package com.simply.birthdayapp.auth.signIn.data.mapper

import com.simply.birthdayapp.auth.signIn.data.model.LoginInputData
import com.simply.birthdayapp.auth.signIn.domain.model.LoginInputDomain

fun LoginInputDomain.toLoginDataModel() = LoginInputData(
    email = this.email,
    password = this.password
)

fun LoginInputData.toLoginDomainModel() = LoginInputDomain(
    email = this.email,
    password = this.password
)