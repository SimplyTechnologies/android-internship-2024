package com.simply.birthdayapp.commonpresentation.components.button

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.simply.birthdayapp.commonpresentation.theme.ButtonTextColor
import com.simply.birthdayapp.commonpresentation.theme.ButtonEnableColor
import com.simply.birthdayapp.commonpresentation.theme.DisableButtonColorWithAlpha
import com.simply.birthdayapp.commonpresentation.theme.ButtonTextStyle

@Composable
fun AuthedButton(
    modifier: Modifier = Modifier,
    text: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        enabled = isEnabled,
        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor = DisableButtonColorWithAlpha,
            disabledContentColor = ButtonEnableColor,
            containerColor = ButtonEnableColor,
            contentColor = ButtonTextColor
        ),
        onClick = onClick
    ) {
        Text(text = text, modifier = Modifier.padding(6.dp) , style = ButtonTextStyle)
    }
}