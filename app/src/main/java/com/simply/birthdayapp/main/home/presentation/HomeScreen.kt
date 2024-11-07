package com.simply.birthdayapp.main.home.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simply.birthdayapp.commonpresentation.components.actionbar.auth.TopAppBarWithBackButton
import com.simply.birthdayapp.commonpresentation.theme.AppBackgroundColor
import com.simply.birthdayapp.commonpresentation.theme.DarkPink
import com.simply.birthdayapp.core.result.Result
import com.simply.birthdayapp.main.home.presentation.component.item.BirthdayItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
) {
    val context = LocalContext.current
    val birthdayList by viewModel.birthdaysUiState.collectAsState()

    when (val uiState = birthdayList) {
        is Result.Error -> {
            Toast.makeText(context, uiState.message, Toast.LENGTH_SHORT).show()
        }

        is Result.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(strokeWidth = 4.dp, color = DarkPink)
            }
        }

        is Result.Success -> {
            Column(modifier = modifier) {
                TopAppBarWithBackButton(
                    Modifier.fillMaxWidth(),
                    showInCenter = true,
                    showTopBar = true
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 52.dp)
                ) {
                    items(uiState.data, key = { item -> item.id }) {
                        BirthdayItem(
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp, vertical = 8.dp),
                            item = it
                        )
                    }
                }
            }

        }
    }
}


@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        Modifier
            .fillMaxSize()
            .background(AppBackgroundColor)
    )
}