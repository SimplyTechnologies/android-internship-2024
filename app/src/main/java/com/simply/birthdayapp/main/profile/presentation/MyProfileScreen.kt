package com.simply.birthdayapp.main.profile.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simply.birthdayapp.R
import com.simply.birthdayapp.commonpresentation.components.button.AccountOptionButton
import com.simply.birthdayapp.commonpresentation.components.image.NetworkImage
import com.simply.birthdayapp.commonpresentation.theme.AppBackgroundColor
import com.simply.birthdayapp.commonpresentation.theme.BirthdayItemTextStyle

@Composable
fun MyProfileScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.height(44.dp)
            )
        }

        MyProfileScreenContent(Modifier.fillMaxSize())
    }
}


@Composable
fun MyProfileScreenContent(modifier: Modifier = Modifier) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(48.dp))
        NetworkImage(modifier = Modifier.size(100.dp), "https://picsum.photos/200")

        Text(
            modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
            text = "Shirley Peter",
            style = BirthdayItemTextStyle,
            fontSize = 20.sp
        )
        Text(
            modifier = Modifier.padding(bottom = 52.dp),
            text = "ShirleyPeter01@gmail.com",
            style = BirthdayItemTextStyle,
            fontSize = 20.sp
        )

        AccountOptionButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = "Change Password"
        )
        AccountOptionButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = "Edit Account"
        )
        AccountOptionButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = "Sign Out"
        )
    }
}


@Preview
@Composable
private fun MyProfileScreenPreview() {
    MyProfileScreen(
        Modifier
            .fillMaxSize()
            .background(AppBackgroundColor)
    )
}