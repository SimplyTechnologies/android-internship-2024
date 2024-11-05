package com.simply.birthdayapp.main.shop.presentation.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simply.birthdayapp.R
import com.simply.birthdayapp.commonpresentation.components.actionbar.auth.TopAppBarWithBackButton
import com.simply.birthdayapp.commonpresentation.components.image.NetworkImage
import com.simply.birthdayapp.commonpresentation.theme.DarkGray
import com.simply.birthdayapp.main.shop.domain.model.ShopDomainModel
import com.simply.birthdayapp.main.shop.presentation.components.clickableTextStyle
import com.simply.birthdayapp.main.shop.presentation.components.ShopRating
import com.simply.birthdayapp.main.shop.presentation.components.noneClickableTextStyle

@Composable
fun ShopDetailsScreen(
    data: ShopDomainModel,
    navigateToShopScreen: () -> Unit
) {
    val context = LocalContext.current
    val googleMapPackage = "com.google.android.apps.maps"
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        TopAppBarWithBackButton(
            onBackPress = { navigateToShopScreen() }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NetworkImage(
                url = data.avatarUrl,
                modifier = Modifier.size(100.dp),
                border = BorderStroke(1.dp, DarkGray)
            )

            Text(
                text = data.name,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 18.dp)
            )

            ShopRating(rating = data.rate ?: 0.0)

            data.let { shop ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row {
                        Text(
                            text = stringResource(R.string.shop_phone_number),
                            style = noneClickableTextStyle
                        )
                        Text(
                            text = shop.phone ?: "",
                            style = clickableTextStyle,
                            modifier = Modifier.clickable {
                                shop.phone?.let {
                                    val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                                        this.data = Uri.parse("tel:${shop.phone}")
                                    }
                                    context.startActivity(dialIntent)
                                }
                            }
                        )
                    }

                    Row(
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.shop_address),
                            style = noneClickableTextStyle
                        )
                        Text(
                            text = shop.address,
                            style = clickableTextStyle,
                            modifier = Modifier.clickable {
                                val googleMapIntentUri =
                                    Uri.parse("geo:0,0?q=${Uri.encode(shop.address)}")
                                val mapIntent = Intent(
                                    Intent.ACTION_VIEW,
                                    googleMapIntentUri
                                ).setPackage(googleMapPackage)
                                context.startActivity(mapIntent)
                            }
                        )
                    }

                    shop.siteUrl?.let { url ->
                        Text(
                            text = stringResource(R.string.shop_web_site),
                            style = clickableTextStyle,
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .clickable {
                                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                    context.startActivity(browserIntent)
                                }
                        )
                    }
                }
            }
        }
    }
}