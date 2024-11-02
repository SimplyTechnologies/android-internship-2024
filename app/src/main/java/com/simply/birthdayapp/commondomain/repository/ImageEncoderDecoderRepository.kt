package com.simply.birthdayapp.commondomain.repository

import android.content.Context
import android.net.Uri

interface ImageEncoderDecoderRepository {
    fun encodeImageToBase64(imageUri: String, context: Context): String?
}