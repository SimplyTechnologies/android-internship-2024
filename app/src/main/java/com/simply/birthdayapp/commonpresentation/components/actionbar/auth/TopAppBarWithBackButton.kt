package com.simply.birthdayapp.commonpresentation.components.actionbar.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simply.birthdayapp.R
import com.simply.birthdayapp.commonpresentation.theme.DarkPink

@Composable
fun TopAppBarWithBackButton(
    modifier: Modifier = Modifier,
    showTopBar: Boolean = true,
    showBackButton: Boolean = true,
    showInCenter: Boolean = false,
    onBackPress: () -> Unit = {},
) {
    AnimatedVisibility(modifier = modifier.fillMaxWidth(), visible = showTopBar) {
        Row(
            horizontalArrangement = if (showInCenter) Arrangement.Center
            else if (showBackButton) Arrangement.SpaceBetween
            else Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (showBackButton && !showInCenter) {
                Box {
                    IconButton(modifier = Modifier.height(40.dp), onClick = onBackPress) {
                        Icon(
                            modifier = Modifier.size(width = 16.dp, height = 24.dp),
                            painter = painterResource(id = R.drawable.ic_navigate_back),
                            contentDescription = null,
                            tint = DarkPink,
                        )
                    }
                }
            }

            Image(
                modifier = Modifier.height(40.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
            )
        }
    }
}

@Preview
@Composable
private fun ActionBarPrev() {
    TopAppBarWithBackButton(
        showTopBar = true,
        showBackButton = true,
        showInCenter = true
    ) {

    }
}