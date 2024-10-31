package com.simply.birthdayapp

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import com.simply.birthdayapp.commonpresentation.navigation.AppNavigation
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val mainViewModel: MainViewModel = koinViewModel()
            WindowInsetsControllerCompat(
                this.window, this.window.decorView
            ).isAppearanceLightStatusBars = true

            val navController = rememberNavController()
            val startDestination by mainViewModel.startDestination.collectAsState(null)

            startDestination?.let {
                AppNavigation(
                    navController = navController,
                    startDestination = it,
                )
            }
        }
    }
}
