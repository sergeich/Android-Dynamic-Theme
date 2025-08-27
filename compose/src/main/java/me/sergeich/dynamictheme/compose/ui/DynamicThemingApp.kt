package me.sergeich.dynamictheme.compose.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import me.sergeich.dynamictheme.compose.ThemeViewModel

@Composable
fun DynamicThemingApp(viewModel: ThemeViewModel) {
    val themeColors = viewModel.themeColors.collectAsState()

    val colorScheme = lightColorScheme(
        primary = themeColors.value.primary,
        secondary = themeColors.value.secondary,
        background = themeColors.value.background,
        surface = themeColors.value.surface,
        onPrimary = themeColors.value.onPrimary
    )

    MaterialTheme(
        colorScheme = colorScheme
    ) {
        MainScreen(onLoadTheme = { viewModel.loadTheme() })
    }
}
