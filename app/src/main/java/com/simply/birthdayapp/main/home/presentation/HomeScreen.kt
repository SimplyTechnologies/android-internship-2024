package com.simply.birthdayapp.main.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simply.birthdayapp.commonpresentation.theme.AppBackgroundColor
import com.simply.birthdayapp.main.components.actionbar.LogoActionBar
import com.simply.birthdayapp.main.home.presentation.component.item.BirthdayItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel = koinViewModel()) {
    val birthdayList by viewModel.birthdays.collectAsState(emptyList())
    Column(modifier = modifier) {
        LogoActionBar(modifier = Modifier.fillMaxWidth())
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(birthdayList, key = { item -> item.id }) {
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


@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        Modifier
            .fillMaxSize()
            .background(AppBackgroundColor)
    )
}