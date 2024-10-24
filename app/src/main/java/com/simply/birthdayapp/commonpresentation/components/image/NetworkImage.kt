package com.simply.birthdayapp.commonpresentation.components.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    url: String?,
    shape: RoundedCornerShape = CircleShape,
    errorImageRes: Int = R.drawable.ic_error
) {
    Image(
        modifier = Modifier
            .clip(shape)
            .size(72.dp),
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
    NetworkImage(url = "https://s3-alpha-sig.figma.com/img/54a6/abbe/eadb372e3e013ab667401f489eaf9aa1?Expires=1730678400&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=aSJVU84B4XWFErfqgde1x5dZzWHgiI3yEXUXfUK27YGcocPOcmF115XEogJj7cWWD~Acxg3PZfrJ9xKiBw7cJPAI6PX95iJcLbEdTfwIr~TBPkHtjoiDNLHMvxEOp5Ons6MWAj-MmhptPe-4S04XlCDNhKMy4j-9eZh-O0QIYCB475sLdsZrVFyqN6k8hATvKT1~puoaQtHdJocWgMrU-ZbRArCtVBe64MJ2te6eZDEFlrI1adnyNGTUwwQlU-fl9FN42HakQVPvtinEk3mcJjKhkIjsPeKWMR-ADNdoTkWCzOIXh6w4oWAOG5ulSBOVS~UfaLubBUe~3LjQv~Q2pw__")
}