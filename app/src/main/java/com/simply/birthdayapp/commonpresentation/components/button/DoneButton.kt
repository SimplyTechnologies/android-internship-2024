package com.simply.birthdayapp.commonpresentation.components.button

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.simply.birthdayapp.commonpresentation.theme.ButtonTextStyle
import com.simply.birthdayapp.commonpresentation.theme.DarkPink

@Composable
fun DoneButton(
    modifier: Modifier = Modifier,
    text: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier,
        enabled = isEnabled,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor = DarkPink.copy(alpha = 0.5f),
            disabledContentColor = Color.White,
            containerColor = DarkPink,
            contentColor = Color.White
        ),
        onClick = onClick
    ) {
        Text(text = text, modifier = Modifier.padding(6.dp), style = ButtonTextStyle)
    }
}