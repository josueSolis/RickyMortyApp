package com.omni.pro.characters.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.omni.pro.characters.android.ui.MainScreen
import com.omni.pro.characters.android.ui.theme.CharactersAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CharactersAppTheme (dynamicColor = false) {
                MainScreen()
            }
        }
    }
}

