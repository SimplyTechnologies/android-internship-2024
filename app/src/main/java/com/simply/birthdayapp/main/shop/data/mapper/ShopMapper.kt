package com.simply.birthdayapp.main.shop.data.mapper

import com.simply.birthdayapp.main.shop.data.model.ShopDataModel
import com.simply.birthdayapp.main.shop.domain.model.ShopDomainModel

fun ShopDomainModel.toDataModel() = ShopDataModel(
    id = this.id,
    name = this.name,
    avatarUrl = this.avatarUrl,
)

fun ShopDataModel.toDomainModel() = ShopDomainModel(
    id = id,
    name = name,
    avatarUrl = avatarUrl,
)