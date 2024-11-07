package com.simply.birthdayapp.commonpresentation.components.image

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.simply.birthdayapp.R
import com.simply.birthdayapp.commonpresentation.theme.LightPink

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier.size(72.dp),
    url: String?,
    shape: RoundedCornerShape = CircleShape,
    defaultPersonImageRes: Int = R.drawable.ic_default_person,
    border: BorderStroke? = null,
) {
    val isPlaceholder = url.isNullOrEmpty()
    val imagePainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(data = url).apply {
            fallback(defaultPersonImageRes)
            transformations(CircleCropTransformation())
        }.build(),
        contentScale = ContentScale.FillBounds,
    )

    if (isPlaceholder) {
        Box(
            modifier = modifier
                .clip(shape)
                .border(BorderStroke((2).dp, LightPink), shape = shape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(48.dp)
                    .padding(8.dp),
                painter = painterResource(R.drawable.ic_default_person),
                contentDescription = null,
                tint = LightPink
            )
        }
    } else {
        Image(
            modifier = modifier
                .clip(shape)
                .border(border ?: BorderStroke((-1).dp, Color.Black), shape = shape),
            painter = imagePainter,
            contentDescription = null,
        )
    }

}


@Preview(showSystemUi = true)
@Composable
fun PreviewNetworkImage() {
    NetworkImage(url = null)
}
