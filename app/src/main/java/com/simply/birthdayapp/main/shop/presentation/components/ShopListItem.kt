package com.simply.birthdayapp.main.shop.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simply.birthdayapp.commonpresentation.components.image.NetworkImage

@Composable
fun ShopListItem(shopName: String, avatarUrl: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .clip(RoundedCornerShape(24.dp)),
        backgroundColor = Color.White,
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            NetworkImage(
                url = avatarUrl,
                border = BorderStroke(1.dp, Color.Black)
            )
            Spacer(
                modifier = Modifier
                    .width(12.dp)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 20.sp,
                text = shopName,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h6,
                color = Color.Black
            )
        }
    }
}
