package com.simply.birthdayapp

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import com.simply.birthdayapp.commonpresentation.navigation.AppNavigation
import com.simply.birthdayapp.commonpresentation.theme.LogoBackground

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // for system status bar color
            WindowInsetsControllerCompat(
                this.window,
                this.window.decorView
            ).isAppearanceLightStatusBars = true

            val navController = rememberNavController()

            AppNavigation(
                modifier = Modifier.background(LogoBackground), navController
            )
        }
    }
}


