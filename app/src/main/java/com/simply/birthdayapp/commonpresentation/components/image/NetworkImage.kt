package com.simply.birthdayapp.commonpresentation.components.image

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.simply.birthdayapp.R

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier.size(72.dp),
    url: String?,
    shape: RoundedCornerShape = CircleShape,
    errorImageRes: Int = R.drawable.ic_error,
    border: BorderStroke? = null,
) {
    Image(
        modifier = Modifier
            .clip(shape)
            .size(72.dp)
            .border(border ?: BorderStroke((-1).dp, Color.Black),shape = shape),
        painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current).data(data = url)
                .apply(block = fun ImageRequest.Builder.() {
                    error(errorImageRes) // if null
                    fallback(errorImageRes) // if url is empty or can't lad
                    transformations(CircleCropTransformation())
                }).build(),
            contentScale = ContentScale.FillBounds,
        ),
        contentDescription = null,
    )
}


@Preview(showSystemUi = true)
@Composable
fun PreviewNetworkImage() {
    NetworkImage(url = null )
}