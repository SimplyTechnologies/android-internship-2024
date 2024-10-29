package com.simply.birthdayapp.main.shop.presentation.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simply.birthdayapp.commonpresentation.theme.SearchBarHintText

@Composable
fun SearchBar() {
    var text by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 20.dp)
            .width(345.dp)
            .height(40.dp)
            .clip(RoundedCornerShape(41.dp))
            .background(Color.White)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        if (text.isEmpty()) {
            Text(
                text = "Search",
                color = Color.Gray,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier.align(Alignment.CenterStart)
            )
        }
        BasicTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 14.sp
            ),
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        innerTextField()
                    }
                    Icon(
                        Icons.Default.Search,
                        contentDescription = null,
                        tint = SearchBarHintText,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        )
    }
}

