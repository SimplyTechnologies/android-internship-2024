package com.simply.birthdayapp.main.profile.profile.data.mapper

import com.simply.birthdayapp.main.profile.profile.data.model.UserData
import com.simply.birthdayapp.main.profile.profile.domain.model.UserDomain

object ProfileMapper {
    fun toDomain(profile: UserData): UserDomain = UserDomain(
        id = profile.id,
        email = profile.email,
        firstName = profile.firstName,
        lastName = profile.lastName,
        image = profile.image,
    )

    fun toData(profile: UserDomain): UserData = UserData(
        id = profile.id,
        email = profile.email,
        firstName = profile.firstName,
        lastName = profile.lastName,
        image = profile.image,
    )
}