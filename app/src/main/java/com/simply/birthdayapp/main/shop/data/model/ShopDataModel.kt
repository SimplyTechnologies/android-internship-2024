package com.simply.birthdayapp.main.shop.data.model

data class ShopDataModel(
    val id: Int,
    val name: String,
    val avatarUrl: String,
    val phone: String?,
    val address: String,
    val siteUrl: String?,
    val rate: Double?
)