package com.simply.birthdayapp.main.shop.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.simply.birthdayapp.main.shop.domain.model.ShopDomainModel
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

object ShopNavType {
    val ShopDomainType = object : NavType<ShopDomainModel>(isNullableAllowed = false) {

        override fun get(bundle: Bundle, key: String): ShopDomainModel? {
            return Json.decodeFromString(string = bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): ShopDomainModel {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: ShopDomainModel): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: ShopDomainModel) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}