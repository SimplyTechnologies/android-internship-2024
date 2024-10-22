package com.simply.birthdayapp.commonpresentation.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.simply.birthdayapp.R

val AuthTitleTextStyle = TextStyle(
    fontSize = 30.sp,
    fontWeight = FontWeight.W700,
    fontFamily = FontFamily(Font(R.font.karma_medium)),
    lineHeight = 48.84.sp,
    letterSpacing = 0.sp,
    color = ButtonPurple
)

val TextFieldTextStyle = TextStyle(
    fontSize = 14.sp,
    fontWeight = FontWeight.W400,
    fontFamily = FontFamily(Font(R.font.karma)),
    lineHeight = 20.34.sp,
    color = TextFieldTextColor
)

val TextFieldPlaceholderStyle = TextStyle(
    fontSize = 14.sp,
    fontWeight = FontWeight.W400,
    fontFamily = FontFamily(Font(R.font.karma_medium)),
    lineHeight = 20.34.sp,
    color = TextFieldTextColor
)

val ButtonTextStyle = TextStyle(
    fontSize = 15.sp,
    fontWeight = FontWeight.W700,
    fontFamily = FontFamily(Font(R.font.karma_medium)),
    lineHeight = 24.42.sp
)




