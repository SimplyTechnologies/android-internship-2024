package com.simply.birthdayapp.commonpresentation.components.image

sealed class ImageSource(val source: String?) {
    data object Unknown : ImageSource(null)
    data class Url(val url: String) : ImageSource(url)
    data class Uri(val uri: String) : ImageSource(uri)
}
