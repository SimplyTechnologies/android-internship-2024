package com.simply.birthdayapp.commonpresentation.components.button

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.simply.birthdayapp.commonpresentation.theme.DarkPink
import com.simply.birthdayapp.commonpresentation.theme.LightPink
import com.simply.birthdayapp.commonpresentation.theme.ButtonTextStyle
import com.simply.birthdayapp.commonpresentation.theme.LightPink

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
            disabledContainerColor = LightPink,
            disabledContentColor = DarkPink,
            containerColor = DarkPink,
            contentColor = LightPink
        ),
        onClick = onClick
    ) {
        Text(text = text, modifier = Modifier.padding(6.dp), style = ButtonTextStyle)
    }
}