package com.simply.birthdayapp.auth.registration.data.mapper

import com.simply.birthdayapp.auth.registration.data.model.UserDataModel
import com.simply.birthdayapp.auth.registration.domain.model.UserDomainModel

fun UserDomainModel.toUserDataModel() = UserDataModel(
    id = this.id,
    email = this.email,
    firstName = this.firstName,
    lastName = this.lastName,
    image = this.image
)

fun UserDataModel.toUserDomainModel() = UserDomainModel(
    id = this.id,
    email = this.email,
    firstName = this.firstName,
    lastName = this.lastName,
    image = this.image
)