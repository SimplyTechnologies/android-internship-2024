package com.simply.birthdayapp.main.shop.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.simply.birthdayapp.R
import com.simply.birthdayapp.main.shop.domain.model.ShopDomainModel
import com.simply.birthdayapp.main.shop.presentation.components.ShopListItem
import com.simply.birthdayapp.main.shop.presentation.components.SearchBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun ShopScreen(viewModel: ShopViewModel = koinViewModel()) {
    val shops by viewModel.shopsUiState.collectAsState()
    when (val uiState = shops) {
        is ShopListUiState.Success -> {
            ShopContent(
                shops = uiState.data,
            )
        }

        else -> {}
    }
}

@Composable
fun ShopContent(shops: List<ShopDomainModel>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .width(88.dp)
                .height(40.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null
        )
        SearchBar()
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = 24.dp, top = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(shops) { shop ->
                ShopListItem(
                    shopName = shop.name,
                    avatarUrl = shop.avatarUrl,
                )
            }
        }
    }
}


