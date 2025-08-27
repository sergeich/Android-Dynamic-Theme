package me.sergeich.dynamictheme.xml

import kotlinx.coroutines.delay

object ThemeRepository {

    suspend fun fetchThemeColors(): ThemeColorsDto {
        delay(1000)
        return ThemeColorsDto(
            primary = "#63A002",
            secondary = "#85976E",
            onPrimary = "#FFFFFF",
            text = "#883311"
        )
    }

}
