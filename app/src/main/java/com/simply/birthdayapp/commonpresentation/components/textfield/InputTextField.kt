package com.simply.birthdayapp.commonpresentation.components.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simply.birthdayapp.R
import com.simply.birthdayapp.commonpresentation.theme.LightPinkBackground
import com.simply.birthdayapp.commonpresentation.theme.ErrorPink
import com.simply.birthdayapp.commonpresentation.theme.TextFieldPlaceholderStyle
import com.simply.birthdayapp.commonpresentation.theme.TextFieldShape
import com.simply.birthdayapp.commonpresentation.theme.TextFieldTextStyle

class PasswordAsteriskVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val asterisks = AnnotatedString("*".repeat(text.length))
        return TransformedText(asterisks, OffsetMapping.Identity)
    }
}

@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    value: MutableState<String>,
    error: MutableState<String>,
    placeholder: String = "",
    isHasError: Boolean = false,
    isPassword: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        BasicTextField(
            value = value.value,
            onValueChange = { value.value = it },
            singleLine = true,
            textStyle = TextFieldTextStyle.copy(textAlign = TextAlign.Start),
            modifier = Modifier
                .fillMaxWidth()
                .then(modifier),
            visualTransformation = if (isPassword && !passwordVisible) PasswordAsteriskVisualTransformation() else VisualTransformation.None,
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(
                            color = if (isHasError) ErrorPink else LightPinkBackground,
                            shape = TextFieldShape
                        )
                        .padding(horizontal = 12.dp, vertical = 0.dp)
                        .fillMaxWidth()
                ) {
                    if (leadingIcon != null) {
                        leadingIcon()
                    }
                    Box(
                        Modifier
                            .weight(1f)
                            .padding(vertical = 12.dp)
                    ) {
                        if (value.value.isEmpty()) {
                            Text(
                                text = placeholder,
                                style = TextFieldPlaceholderStyle,
                                modifier = Modifier.align(Alignment.CenterStart)
                            )
                        }
                        innerTextField()
                    }
                    if (isPassword && value.value.isNotEmpty()) {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                painter = painterResource(if (passwordVisible) R.drawable.ic_visibility else R.drawable.ic_visibility_off),
                                contentDescription = if (passwordVisible) "Hide password" else "Show password",
                                tint = Color.Unspecified
                            )
                        }
                    }
                    trailingIcon?.invoke()
                }
            }
        )
    }
}

@Preview
@Composable
private fun InputTextFieldPreview() {
    val value = remember { mutableStateOf("") }
    val error = remember { mutableStateOf("") }
    Box(modifier = Modifier.padding(16.dp), contentAlignment = Alignment.Center) {
        InputTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = value,
            error = error,
            placeholder = "Email"
        )
    }
}