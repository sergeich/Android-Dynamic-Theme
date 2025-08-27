package me.sergeich.dynamictheme.compose

import androidx.compose.ui.graphics.Color

data class ThemeColorsDto(
    val primary: String,
    val secondary: String,
    val background: String,
    val surface: String,
    val onPrimary: String
)

data class ThemeColors(
    val primary: Color,
    val secondary: Color,
    val background: Color,
    val surface: Color,
    val onPrimary: Color
)

fun ThemeColorsDto.toThemeColors(): ThemeColors = ThemeColors(
    primary = Color(android.graphics.Color.parseColor(primary)),
    secondary = Color(android.graphics.Color.parseColor(secondary)),
    background = Color(android.graphics.Color.parseColor(background)),
    surface = Color(android.graphics.Color.parseColor(surface)),
    onPrimary = Color(android.graphics.Color.parseColor(onPrimary))
)

object ThemeDefaults {
    val default = ThemeColors(
        primary = Color(0xFF6200EE),
        secondary = Color(0xFF03DAC5),
        background = Color.White,
        surface = Color(0xFFF5F5F5),
        onPrimary = Color.White
    )
}
