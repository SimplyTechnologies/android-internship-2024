package com.simply.birthdayapp.commonpresentation.components.lottie

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.simply.birthdayapp.R

@Composable
fun Animation(modifier: Modifier = Modifier, res: Int) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(res))

    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )

    LottieAnimation(
        modifier = modifier,
        composition = composition,
        progress = { progress }
    )
}

@Preview
@Composable
fun PreviewLottieAnimation() {
    Animation(modifier = Modifier.fillMaxWidth(), res = R.raw.birthday_cong_anim)
}
