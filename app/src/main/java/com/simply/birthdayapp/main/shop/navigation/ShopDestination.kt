package com.simply.birthdayapp.main.shop.navigation

import com.simply.birthdayapp.main.shop.domain.model.ShopDomainModel
import kotlinx.serialization.Serializable

sealed interface ShopDestination {
    @Serializable
    data object ShopScreenDestination : ShopDestination

    @Serializable
    data class ShopDetailsDestination(var shop: ShopDomainModel) : ShopDestination
}