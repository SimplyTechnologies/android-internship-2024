package com.simply.birthdayapp.main.profile.profile.domain.model

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@Serializable
data class UserDomain(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val image: String?,
) {
    companion object {
        val default = UserDomain(
            id = 0,
            email = "",
            firstName = "",
            lastName = "",
            image = null,
        )
    }
}


object UserNavType {
    val UserDomainType = object : NavType<UserDomain>(isNullableAllowed = false) {

        override fun get(bundle: Bundle, key: String): UserDomain? {
            return Json.decodeFromString(string = bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): UserDomain {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: UserDomain): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: UserDomain) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}