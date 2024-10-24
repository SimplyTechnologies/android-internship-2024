package com.simply.birthdayapp.commonpresentation.components.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.simply.birthdayapp.commonpresentation.theme.BirthdayItemTextStyle
import com.simply.birthdayapp.commonpresentation.theme.DarkPink

@Composable
fun AccountOptionButton(
    modifier: Modifier = Modifier, text: String, onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White, contentColor = DarkPink
        )
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            fontSize = 18.sp,
            textAlign = TextAlign.Start,
            style = BirthdayItemTextStyle
        )
    }
}


@Preview
@Composable
private fun AccountOptionButtonPreview() {
    AccountOptionButton(text = "Change Password")
}