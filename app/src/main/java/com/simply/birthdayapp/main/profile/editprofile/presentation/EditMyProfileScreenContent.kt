package com.simply.birthdayapp.main.profile.editprofile.presentation


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.simply.birthdayapp.R
import com.simply.birthdayapp.commonpresentation.components.actionbar.auth.TopAppBarWithBackButton
import com.simply.birthdayapp.commonpresentation.components.button.DoneButton
import com.simply.birthdayapp.commonpresentation.components.image.ProfileImage
import com.simply.birthdayapp.main.profile.chnagepassword.presentation.component.TextFieldWithPlaceholder

@Composable
fun EditMyProfileScreenContent(
    modifier: Modifier = Modifier,
    viewModel: EditMyProfileViewModel,
) {
    val context = LocalContext.current

    val name by viewModel.name.collectAsState()
    val surname by viewModel.surname.collectAsState()
    val imageSource by viewModel.imageSource.collectAsState()

    val nameError by viewModel.nameError.collectAsState()
    val surnameError by viewModel.surnameError.collectAsState()

    val doneButtonEnableState by viewModel.doneButtonEnableState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState())
            .imePadding(),
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
                .fillMaxHeight(0.7f)
                .padding(horizontal = 36.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ProfileImage(
                modifier = Modifier.size(160.dp),
                imageSource = imageSource,
            ) {
                viewModel.uploadImage(it)
            }

            TextFieldWithPlaceholder(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 28.dp, bottom = 8.dp),
                textValue = name,
                error = nameError,
                placeholder = stringResource(R.string.new_password),
                onValueChange = { viewModel.setName(it) },
            )


            TextFieldWithPlaceholder(
                modifier = Modifier.fillMaxWidth(),
                textValue = surname,
                error = surnameError,
                placeholder = stringResource(R.string.new_password),
                onValueChange = { viewModel.setSurname(it) },
            )
        }

        DoneButton(modifier = Modifier.padding(bottom = 74.dp),
            isEnabled = doneButtonEnableState,
            text = stringResource(R.string.done),
            onClick = {
                viewModel.editProfile(context = context)
            })
    }
}