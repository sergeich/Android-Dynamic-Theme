package me.sergeich.dynamictheme.xml

import android.graphics.Color
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class ThemeColorsDto(
    val primary: String,
    val secondary: String,
    val onPrimary: String,
    val text: String
)

@Parcelize
data class ThemeColors(
    val primary: Int,
    val secondary: Int,
    val onPrimary: Int,
    val text: Int,
) : Parcelable

fun ThemeColorsDto.toThemeColors(): ThemeColors = ThemeColors(
    primary = Color.parseColor(primary),
    secondary = Color.parseColor(secondary),
    onPrimary = Color.parseColor(onPrimary),
    text = Color.parseColor(text),
)
