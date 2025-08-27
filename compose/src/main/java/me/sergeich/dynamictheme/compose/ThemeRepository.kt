package me.sergeich.dynamictheme.compose

import kotlinx.coroutines.delay

object ThemeRepository {
    suspend fun fetchThemeColors(): ThemeColorsDto {
        delay(1000) // Simulate network call
        return ThemeColorsDto(
            primary = "#006EAD",
            secondary = "#00C853",
            background = "#FFFFFF",
            surface = "#F5F5F5",
            onPrimary = "#FFFFFF"
        )
    }
}
