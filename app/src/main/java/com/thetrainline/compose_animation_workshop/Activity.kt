package com.thetrainline.compose_animation_workshop

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        val dynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

        setContent {
            val context = LocalContext.current
            val darkTheme = isSystemInDarkTheme()

            MaterialTheme(
                colorScheme = when {
                    dynamicColor && darkTheme -> dynamicDarkColorScheme(context)
                    dynamicColor && !darkTheme -> dynamicLightColorScheme(context)
                    darkTheme -> darkColorScheme()
                    else -> lightColorScheme()
                }
            ) {
                Navigator()
            }
        }
    }
}


