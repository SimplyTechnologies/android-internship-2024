package com.simply.birthdayapp.auth.landing.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simply.birthdayapp.R
import com.simply.birthdayapp.commonpresentation.theme.DarkPink
import com.simply.birthdayapp.commonpresentation.theme.LightPink

@Composable
fun LandingScreen(modifier: Modifier, onSignInClick: () -> Unit, onRegisterClick: () -> Unit) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Image(
            modifier = Modifier.height(96.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(56.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 48.dp),
            onClick = onSignInClick,
            colors = ButtonDefaults.buttonColors(containerColor = LightPink),
            shape = RoundedCornerShape(
                topStart = 40.dp, bottomStart = 0.dp, topEnd = 40.dp, bottomEnd = 40.dp
            )
        ) {
            Text(
                text = stringResource(R.string.sign_in),
                fontFamily = FontFamily(Font(R.font.karma_medium)),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = DarkPink
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 48.dp),
            onClick = onRegisterClick,
            colors = ButtonDefaults.buttonColors(containerColor = DarkPink),
            shape = RoundedCornerShape(
                topStart = 40.dp, bottomStart = 40.dp, topEnd = 0.dp, bottomEnd = 40.dp
            )
        ) {
            Text(
                text = stringResource(R.string.register),
                fontFamily = FontFamily(Font(R.font.karma_medium)),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = LightPink
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun LandingScreenPreview() {
    LandingScreen(modifier = Modifier, onSignInClick = {}, onRegisterClick = {})
}
