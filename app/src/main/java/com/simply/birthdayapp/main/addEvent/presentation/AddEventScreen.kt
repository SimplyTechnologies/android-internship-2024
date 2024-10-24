package com.simply.birthdayapp.main.addEvent.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.simply.birthdayapp.auth.signIn.presentation.SignInViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddEventScreen(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = koinViewModel(),
) {
    Box(
        modifier = Modifier
            .fillMaxSize() // Fills the entire screen
    ) {
        Text(
            text = "Add Event Screen",
            modifier = Modifier.align(Alignment.Center), // Centers the text in the Box
            style = TextStyle(fontSize = 24.sp) // Optional: Set text size or style
        )
    }
}