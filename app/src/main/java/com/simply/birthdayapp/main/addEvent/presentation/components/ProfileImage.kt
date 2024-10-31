package com.simply.birthdayapp.main.addEvent.presentation.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.simply.birthdayapp.R
import com.simply.birthdayapp.commonpresentation.theme.AddImageBackgroundColor
import com.simply.birthdayapp.commonpresentation.theme.DarkPink

@Composable
fun ProfileImage(
    imageUrl: Uri?,
    onAddPhotoClick: (Uri?) -> Unit
) {
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            onAddPhotoClick.invoke(it)
        }
    )
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .background(AddImageBackgroundColor)
    ) {
        if (imageUrl != null) {
            AsyncImage(
                model = imageUrl,
                contentDescription = stringResource(R.string.profile_image_description),
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
            )
        } else {
            Icon(
                modifier = Modifier
                    .align(Alignment.Center)
                    .clickable {
                        photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    },
                imageVector = ImageVector.vectorResource(R.drawable.ic_image_plus),
                contentDescription = stringResource(R.string.add_photo_description),
                tint = DarkPink

            )
        }
    }
}