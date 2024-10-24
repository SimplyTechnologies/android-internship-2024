package com.simply.birthdayapp.commonpresentation.components.actionbar.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.simply.birthdayapp.R
import com.simply.birthdayapp.commonpresentation.theme.DarkPink

@Composable
fun AuthActionBar(
    modifier: Modifier = Modifier,
    showTopBar: Boolean = true,
    onBackPress: () -> Unit,
) {
    AnimatedVisibility(modifier = modifier.fillMaxWidth(), visible = showTopBar) {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Box(Modifier.padding(top = 27.dp)) {
                IconButton(onClick = onBackPress) {
                    Icon(
                        modifier = Modifier.size(width = 16.dp, height = 24.dp),
                        painter = painterResource(id = R.drawable.ic_navigate_back),
                        contentDescription = null,
                        tint = DarkPink
                    )
                }
            }
            Image(
                modifier = Modifier.size(width = 88.dp, height = 40.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null
            )
        }
    }
}