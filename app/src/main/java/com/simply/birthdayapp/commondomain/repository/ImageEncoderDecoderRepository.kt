package com.simply.birthdayapp.commondomain.repository

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri

interface ImageEncoderDecoderRepository {
    fun encodeImageToBase64(imageUri: Uri?, context: Context): String?
    fun decodeBase64ToBitmap(base64String: String?): Bitmap?
}