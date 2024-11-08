package com.simply.birthdayapp.main.shop.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.unit.dp
import com.simply.birthdayapp.commonpresentation.theme.LightGray
import com.simply.birthdayapp.commonpresentation.theme.Orange

@Composable
fun ShopRating(
    rating: Double = 0.0,
    maxRating: Int = 5,
) {
    Row(
        modifier = Modifier.padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..maxRating) {
            val currentStarFill = when {
                (i <= rating) -> 1f
                ((i - 1) < rating && i > rating) -> (rating - (i - 1)).toFloat()
                else -> 0f
            }

            Box(
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = LightGray
                )

                if (currentStarFill > 0f) {
                    Icon(
                        modifier = Modifier
                            .fillMaxSize()
                            .clipStar(currentStarFill),
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Orange,
                    )
                }
            }
        }
    }
}

fun Modifier.clipStar(fraction: Float): Modifier = this.then(
    Modifier.drawWithContent {
        val width = size.width * fraction
        clipRect(right = width) {
            this@drawWithContent.drawContent()
        }
    }
)