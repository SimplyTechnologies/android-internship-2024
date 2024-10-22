package com.simply.birthdayapp

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.simply.birthdayapp.commonpresentation.navigation.AppNavigation
import com.simply.birthdayapp.commonpresentation.theme.LogoBackground

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            Scaffold(modifier = Modifier.fillMaxSize(),
                topBar = {},
                bottomBar = {}) { innerPadding ->
                AppNavigation(
                    modifier = Modifier
                        .padding(innerPadding)
                        .background(LogoBackground),
                    navController
                )
            }
        }
    }
}


