package com.simply.birthdayapp.main.profile.editprofile.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.simply.birthdayapp.R
import com.simply.birthdayapp.commonpresentation.theme.PrimaryTextStyle

@Composable
fun EditMyProfileScreen(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.fillMaxSize(),
        text = stringResource(R.string.edit_account),
        textAlign = TextAlign.Center,
        style = PrimaryTextStyle
    )
}