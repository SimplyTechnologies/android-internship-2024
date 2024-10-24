package com.simply.birthdayapp.commonpresentation.components.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simply.birthdayapp.R
import com.simply.birthdayapp.commonpresentation.theme.AuthErrorTextStyle
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
    textValue: String,
    error: String?,
    placeholder: String = "",
    isPassword: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(imeAction = androidx.compose.ui.text.input.ImeAction.Next),
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    onFocusChange: (Boolean) -> Unit = {}
) {
    var passwordVisible by remember { mutableStateOf(false) }
    val hasError = error?.isNotEmpty()
    var hasFocusBefore by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(modifier = modifier) {
        Column {
            BasicTextField(value = textValue,
                onValueChange = { onValueChange(it) },
                singleLine = true,
                textStyle = TextFieldTextStyle,
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                        onFocusChange(false)
                    },
                ),
                keyboardOptions = keyboardOptions,
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        if (!focusState.isFocused && hasFocusBefore) {
                            onFocusChange(focusState.isFocused)
                        }
                        if (focusState.isFocused) {
                            hasFocusBefore = true
                        }
                    },
                visualTransformation = if (isPassword && !passwordVisible) PasswordAsteriskVisualTransformation() else VisualTransformation.None,
                decorationBox = { innerTextField ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(
                                color = if (hasError == true) ErrorPink else LightPinkBackground,
                                shape = TextFieldShape
                            )
                            .border(
                                1.dp,
                                if (hasError == true) Color.Red else Color.Transparent,
                                TextFieldShape
                            )
                            .padding(start = 12.dp)
                            .fillMaxWidth()
                    ) {
                        leadingIcon?.invoke()

                        Box(
                            Modifier
                                .weight(1f)
                                .padding(vertical = 12.dp)
                        ) {
                            if (textValue.isEmpty()) {
                                Text(
                                    text = placeholder,
                                    style = TextFieldPlaceholderStyle,
                                    modifier = Modifier.align(Alignment.CenterStart)
                                )
                            }
                            innerTextField()
                        }
                        if (isPassword && textValue.isNotEmpty()) {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    painter = painterResource(if (passwordVisible) R.drawable.ic_visibility else R.drawable.ic_visibility_off),
                                    contentDescription = null,
                                    tint = Color.Unspecified
                                )
                            }
                        }

                        trailingIcon?.invoke()
                    }
                })
            if (hasError == true) {
                Text(
                    text = error,
                    modifier = Modifier.padding(start = 5.dp, top = 8.dp),
                    style = AuthErrorTextStyle
                )
            }
        }
    }
}

@Preview
@Composable
private fun InputTextFieldPreview() {
    val value = remember { mutableStateOf("") }
    val error = remember { mutableStateOf<String?>("") }
    Box(modifier = Modifier.padding(16.dp), contentAlignment = Alignment.Center) {
        InputTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            textValue = value.value,
            error = error.value,
            placeholder = stringResource(R.string.email),
            onValueChange = { value.value = it },
            onFocusChange = {})
    }
}