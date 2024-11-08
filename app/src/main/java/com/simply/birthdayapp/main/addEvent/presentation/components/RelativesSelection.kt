package com.simply.birthdayapp.main.addEvent.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.simply.birthdayapp.commonpresentation.theme.ColumnItemsShape
import com.simply.birthdayapp.commonpresentation.theme.DarkPink
import com.simply.birthdayapp.commonpresentation.theme.PrimaryTextStyle

@Composable
fun RelativesSelection(
    relatives: List<String>,
    selectedRelative: String,
    onChangeSelectedRelative: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .heightIn(max = 500.dp)
        ) {
            items(relatives.size) { index ->
                val relative = relatives[index]
                Box(
                    modifier = Modifier
                        .height(37.dp)
                        .clip(ColumnItemsShape)
                        .background(
                            if (selectedRelative == relative) DarkPink else Color.White,
                        )
                        .clickable {
                            onChangeSelectedRelative(relative)
                        },

                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        modifier = Modifier
                            .padding(vertical = 8.dp),
                        text = relative,
                        color = if (selectedRelative == relative) Color.White else Color.Black,
                        style = PrimaryTextStyle,
                        maxLines = 1
                    )

                }
            }
        }
    }
}