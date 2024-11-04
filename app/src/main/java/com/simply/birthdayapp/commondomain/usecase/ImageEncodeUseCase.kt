package com.simply.birthdayapp.commondomain.usecase

import android.content.Context
import android.net.Uri
import com.simply.birthdayapp.commondomain.repository.ImageEncoderDecoderRepository

interface ImageEncodeUseCase {
    fun invoke(imageUri: Uri?, context: Context): String?
}

class ImageEncodeUseCaseImpl(private val repository: ImageEncoderDecoderRepository) :
    ImageEncodeUseCase {

    override fun invoke(imageUri: Uri?, context: Context): String? {
        return repository.encodeImageToBase64(imageUri, context)
    }
}