package com.simply.birthdayapp.commondomain.repository

import android.content.Context

interface ImageEncoderDecoderRepository {
    fun encodeImageToBase64(imageUri: String, context: Context): String?
}