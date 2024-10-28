package com.simply.birthdayapp.main.components.actionbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.simply.birthdayapp.R
import com.simply.birthdayapp.commonpresentation.theme.AppBackgroundColor

@Composable
fun LogoActionBar(modifier: Modifier = Modifier) {
    Box(modifier = modifier.background(AppBackgroundColor), contentAlignment = Alignment.Center) {
        Image(
            modifier = Modifier.height(40.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null
        )
    }
}