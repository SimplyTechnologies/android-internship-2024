package com.simply.birthdayapp.main.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simply.birthdayapp.R
import com.simply.birthdayapp.commondomain.model.Birthday
import com.simply.birthdayapp.commonpresentation.components.image.NetworkImage
import com.simply.birthdayapp.commonpresentation.components.lottie.Animation
import com.simply.birthdayapp.commonpresentation.ext.isToday
import com.simply.birthdayapp.commonpresentation.theme.AppBackgroundColor
import com.simply.birthdayapp.commonpresentation.theme.PrimaryTextStyle
import com.simply.birthdayapp.main.home.data.getMockBirthdays


@Composable
fun BirthdayItem(
    modifier: Modifier = Modifier, item: Birthday, onItemClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable { onItemClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // User Image and Details
            Row(modifier = Modifier.weight(1f)) {
                NetworkImage(url = item.image)

                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 44.dp),
                ) {
                    Text(
                        text = item.name,
                        modifier = Modifier.padding(top = 4.dp),
                        style = PrimaryTextStyle,
                        fontSize = 20.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = formatDate(item.date), style = PrimaryTextStyle, fontSize = 18.sp
                    )
                }
            }

            if (item.date.isToday()) {
                Animation(
                    modifier = Modifier.size(48.dp),
                    res = R.raw.congratulations_anim,
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun BirthdayItemPreview() {
    BirthdayItem(
        Modifier
            .fillMaxWidth()
            .background(AppBackgroundColor)
            .padding(16.dp),
        item = getMockBirthdays().first(),
        onItemClick = {})
}