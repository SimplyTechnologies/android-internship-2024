package com.simply.birthdayapp.main.shop.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.simply.birthdayapp.R
import com.simply.birthdayapp.commonpresentation.theme.DarkPink
import com.simply.birthdayapp.main.components.actionbar.LogoActionBar
import com.simply.birthdayapp.main.shop.domain.model.ShopDomainModel
import com.simply.birthdayapp.main.shop.presentation.ShopListUiState
import com.simply.birthdayapp.main.shop.presentation.ShopViewModel
import com.simply.birthdayapp.main.shop.presentation.components.SearchBar
import com.simply.birthdayapp.main.shop.presentation.components.ShopListItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun ShopScreen(
    modifier: Modifier = Modifier,
    viewModel: ShopViewModel = koinViewModel(),
    navigateToShopDetailsScreen: (ShopDomainModel) -> Unit = {}
) {
    val shopsState by viewModel.shopsUiState.collectAsState()
    val searchText by viewModel.searchText.collectAsState()

    Column(
        modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoActionBar(modifier = Modifier.fillMaxWidth())
        SearchBar(text = searchText,
            onClearClick = { viewModel.onClearSearch() },
            onSearchClick = { viewModel.onSearchText(it) })
        when (val uiState = shopsState) {
            is ShopListUiState.Success -> {
                if (uiState.data.isEmpty()) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = stringResource(R.string.search_result),
                        color = Color.Gray
                    )
                } else {
                    LazyColumn(
                        Modifier
                            .fillMaxSize()
                            .padding(
                                start = 24.dp,
                                end = 24.dp,
                                top = 18.dp,
                                bottom = 52.dp,
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(uiState.data) { shop ->
                            ShopListItem(shopName = shop.name,
                                avatarUrl = shop.avatarUrl,
                                onItemClick = { navigateToShopDetailsScreen(shop) })
                        }
                    }
                }
            }

            ShopListUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        strokeWidth = 4.dp, color = DarkPink
                    )
                }
            }

            is ShopListUiState.Error -> {
                Toast.makeText(
                    LocalContext.current, stringResource(R.string.general_error), Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}