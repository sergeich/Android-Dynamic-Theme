package me.sergeich.dynamictheme.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import me.sergeich.dynamictheme.compose.ui.DynamicThemingApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = ThemeViewModel()
            DynamicThemingApp(viewModel)
        }
    }
}
