package com.simply.birthdayapp.main.shop.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ShopDomainModel(
    val id: Int,
    val name: String,
    val avatarUrl: String,
    val phone: String?,
    val address: String,
    val siteUrl: String?,
    val rate: Double?
)