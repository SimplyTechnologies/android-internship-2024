package com.simply.birthdayapp.main.shop.presentation.components

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import com.simply.birthdayapp.commonpresentation.theme.LightBlack

val noneClickableTextStyle = TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight.Bold,
    color = Color.Black
)

val clickableTextStyle = TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight.Medium,
    textDecoration = TextDecoration.Underline,
    color = LightBlack
)