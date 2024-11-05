package com.simply.birthdayapp.main.profile.chnagepassword.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simply.birthdayapp.R
import com.simply.birthdayapp.commonpresentation.components.textfield.InputTextField
import com.simply.birthdayapp.commonpresentation.theme.DarkPink
import com.simply.birthdayapp.commonpresentation.theme.PrimaryTextStyle

@Composable
fun TextFieldWithPlaceholder(
    modifier: Modifier = Modifier,
    placeholder: String = "",
    textValue: String,
    error: Int? = null,
    isPassword: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
    onValueChange: (String) -> Unit = {}
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(start = 4.dp, bottom = 8.dp),
            text = placeholder,
            style = PrimaryTextStyle,
            color = DarkPink.copy(alpha = 0.7f)
        )
        InputTextField(
            modifier = Modifier.fillMaxWidth(),
            textValue = textValue,
            error = error?.let { stringResource(it) },
            onValueChange = onValueChange,
            isPassword = isPassword,
            containerColor = Color.White,
            shape = RoundedCornerShape(24.dp),
            keyboardOptions = keyboardOptions
        )
    }
}


@Preview
@Composable
private fun PasswordTextFieldPreview() {
    val password by remember { mutableStateOf("") }
    TextFieldWithPlaceholder(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        placeholder = stringResource(R.string.change_password),
        textValue = password,
        error = null
    ) {}
}