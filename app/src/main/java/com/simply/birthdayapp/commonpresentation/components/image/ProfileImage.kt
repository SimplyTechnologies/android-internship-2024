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
import coil.ImageLoader
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation


@Composable
fun ProfileImage(
    modifier: Modifier = Modifier.size(100.dp),
    imageSource: ImageSource = ImageSource.Unknown,
    onAddPhotoClick: (Uri?) -> Unit = {}
) {
    val context = LocalContext.current
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { onAddPhotoClick(it) },
    )

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(imageSource.source)
            .transformations(
                CircleCropTransformation()
            ).build(),
        imageLoader = ImageLoader(context),
    )

    Box(
        modifier = modifier.clickable {
            photoPickerLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }
    ) {
        if (imageSource == ImageSource.Unknown || painter.state is AsyncImagePainter.State.Error) {
            ProfileImageFallback(Modifier.fillMaxSize())
        } else {
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}


@Preview
@Composable
private fun AddProfileImageDefaultPreview() {
    ProfileImage(
        modifier = Modifier.size(160.dp),
        imageSource = ImageSource.Unknown,
    )
}