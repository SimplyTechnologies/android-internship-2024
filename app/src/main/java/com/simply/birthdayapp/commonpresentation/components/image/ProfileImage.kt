package com.simply.birthdayapp.commonpresentation.components.image

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation

@Composable
fun ProfileImage(
    modifier: Modifier = Modifier.size(100.dp),
    url: String? = null,
    uri: Uri? = null,
    onAddPhotoClick: (Uri?) -> Unit = {}
) {
    val photoPickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia(),
            onResult = {
                onAddPhotoClick.invoke(it)
            })

    val imageModel = when {
        uri != null -> uri
        url != null -> url
        else -> null
    }

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(imageModel).apply {
            transformations(CircleCropTransformation())
        }.build()
    )

    Box(modifier.clickable {
        photoPickerLauncher.launch(
            PickVisualMediaRequest(
                ActivityResultContracts.PickVisualMedia.ImageOnly
            )
        )
    }) {
        if (painter.state is AsyncImagePainter.State.Error || imageModel == null) {
            ProfileImageFallback(Modifier.fillMaxSize())
        } else {
            Image(
                painter = painter,
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}


@Preview
@Composable
private fun AddProfileImageDefaultPreview() {
    ProfileImage(modifier = Modifier.size(160.dp), url = null)
}