package com.simply.birthdayapp.commondata

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.util.Base64
import androidx.exifinterface.media.ExifInterface
import com.simply.birthdayapp.commondomain.repository.ImageEncoderDecoderRepository
import java.io.ByteArrayOutputStream

object ImageEncodeDecodeHelper : ImageEncoderDecoderRepository {

    override fun encodeImageToBase64(imageUri: Uri?, context: Context): String? {
        if (imageUri == null) return null

        return try {
            val inputStream = context.contentResolver.openInputStream(imageUri)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            val exif = ExifInterface(context.contentResolver.openInputStream(imageUri)!!)
            val orientation = exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )

            val rotatedBitmap = when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> rotateBitmap(bitmap, 90f)
                ExifInterface.ORIENTATION_ROTATE_180 -> rotateBitmap(bitmap, 180f)
                ExifInterface.ORIENTATION_ROTATE_270 -> rotateBitmap(bitmap, 270f)
                else -> bitmap
            }

            // Resize the bitmap to 160x160
            val resizedBitmap = Bitmap.createScaledBitmap(
                rotatedBitmap,
                160,
                160,
                true
            )

            val outputStream = ByteArrayOutputStream()
            resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 50, outputStream)
            Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)
        } catch (e: Exception) {
            null
        }
    }

    override fun decodeBase64ToBitmap(base64String: String?): Bitmap? {
        return try {
            base64String?.let {
                val decodedBytes = Base64.decode(it, Base64.DEFAULT)
                BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun rotateBitmap(bitmap: Bitmap, degrees: Float): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(degrees)
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }
}
