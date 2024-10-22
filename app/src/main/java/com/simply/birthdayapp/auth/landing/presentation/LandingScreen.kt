package com.simply.birthdayapp.auth.landing.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simply.birthdayapp.R
import com.simply.birthdayapp.commonpresentation.theme.ButtonPink
import com.simply.birthdayapp.commonpresentation.theme.ButtonPurple

@Composable
fun LandingScreen(modifier: Modifier, onSignInClick: () -> Unit, onRegisterClick: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 128.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Birth Logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 32.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onSignInClick,
            colors = ButtonDefaults.buttonColors(ButtonPink),
            shape = RoundedCornerShape(
                topStart = 40.dp, bottomStart = 0.dp, topEnd = 40.dp, bottomEnd = 40.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 48.dp)

        ) {
            Text(
                text = "Sign In",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = ButtonPurple

            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onRegisterClick,
            colors = ButtonDefaults.buttonColors(ButtonPurple),
            shape = RoundedCornerShape(
                topStart = 40.dp, bottomStart = 40.dp, topEnd = 0.dp, bottomEnd = 40.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 48.dp)

        ) {
            Text(
                text = "Register",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = ButtonPink
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun LandingScreenPreview() {
    LandingScreen(modifier = Modifier, onSignInClick = {}, onRegisterClick = {})
}
