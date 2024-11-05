package com.simply.birthdayapp.main.profile.chnagepassword.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.simply.birthdayapp.R
import com.simply.birthdayapp.commonpresentation.components.actionbar.auth.TopAppBarWithBackButton
import com.simply.birthdayapp.commonpresentation.components.button.DoneButton
import com.simply.birthdayapp.commonpresentation.theme.DarkPink
import com.simply.birthdayapp.main.profile.chnagepassword.presentation.component.TextFieldWithPlaceholder

@Composable
fun ChangePasswordScreenContent(
    modifier: Modifier = Modifier,
    viewModel: ChangePasswordViewModel,
    isLoading: Boolean,
) {
    val oldPassword by viewModel.oldPassword.collectAsState()
    val newPassword by viewModel.newPassword.collectAsState()
    val repeatNewPassword by viewModel.repeatNewPassword.collectAsState()
    val isEnabled by viewModel.isDoneEnabled.collectAsState()
    val newPasswordError by viewModel.newPasswordError.collectAsState()
    val repeatNewPasswordError by viewModel.repeatNewPasswordError.collectAsState()


    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            TopAppBarWithBackButton(
                modifier = Modifier.fillMaxWidth(),
                showTopBar = true,
                showBackButton = false,
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                TextFieldWithPlaceholder(modifier = Modifier.fillMaxWidth(),
                    textValue = oldPassword,
                    placeholder = stringResource(R.string.old_password),
                    onValueChange = { viewModel.setOldPassword(it) })

                TextFieldWithPlaceholder(modifier = Modifier.fillMaxWidth(),
                    textValue = newPassword,
                    error = newPasswordError,
                    placeholder = stringResource(R.string.new_password),
                    onValueChange = { viewModel.setNewPassword(it) })

                TextFieldWithPlaceholder(modifier = Modifier.fillMaxWidth(),
                    textValue = repeatNewPassword,
                    error = repeatNewPasswordError,
                    placeholder = stringResource(R.string.repeat_new_password),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    onValueChange = { viewModel.setRepeatNewPassword(it) })
            }

            DoneButton(
                modifier = Modifier.padding(bottom = 76.dp),
                text = stringResource(R.string.done),
                isEnabled = isEnabled && !isLoading
            ) {
                viewModel.changePassword()
            }
        }

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    strokeWidth = 3.dp, color = DarkPink
                )
            }
        }
    }
}
