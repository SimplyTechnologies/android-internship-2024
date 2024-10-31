package com.simply.birthdayapp.main.shop.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.simply.birthdayapp.commonpresentation.theme.AppBackgroundColor
import com.simply.birthdayapp.main.shop.navigation.ShopDestination
import com.simply.birthdayapp.main.shop.navigation.ShopNavType
import com.simply.birthdayapp.main.shop.domain.model.ShopDomainModel
import kotlin.reflect.typeOf

@Composable
fun ShopMainScreen(
) {
    val shopNavController = rememberNavController()
    NavHost(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackgroundColor),
        navController = shopNavController,
        startDestination = ShopDestination.ShopScreenDestination
    ) {
        composable<ShopDestination.ShopScreenDestination> {
            ShopScreen {
                shopNavController.navigate(ShopDestination.ShopDetailsDestination(it))
            }
        }
        composable<ShopDestination.ShopDetailsDestination>(
            typeMap = mapOf(typeOf<ShopDomainModel>() to ShopNavType.ShopDomainType)
        ) {
            val shopDomainModel = it.toRoute<ShopDestination.ShopDetailsDestination>().shop
            ShopDetailsScreen(data = shopDomainModel) {
                shopNavController.popBackStack()
            }
        }
    }
}