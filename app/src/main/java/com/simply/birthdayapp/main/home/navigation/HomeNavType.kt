package com.simply.birthdayapp.main.home.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.simply.birthdayapp.commondomain.model.Birthday
import com.simply.birthdayapp.main.navigation.BirthdayMode
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object HomeNavType {
    val HomeDomainType = object : NavType<Birthday>(isNullableAllowed = false) {

        override fun get(bundle: Bundle, key: String): Birthday? {
            return Json.decodeFromString(string = bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): Birthday {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: Birthday): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: Birthday) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}

object BirthdayModeNavType {
    val BirthdayModeType = object : NavType<BirthdayMode>(isNullableAllowed = false) {

        override fun get(bundle: Bundle, key: String): BirthdayMode? {
            return Json.decodeFromString(string = bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): BirthdayMode {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: BirthdayMode): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: BirthdayMode) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}