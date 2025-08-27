package me.sergeich.dynamictheme.compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ThemeViewModel : ViewModel() {
    private val _themeColors = MutableStateFlow(ThemeDefaults.default)
    val themeColors: StateFlow<ThemeColors> = _themeColors

    fun loadTheme() {
        viewModelScope.launch {
            val dto = ThemeRepository.fetchThemeColors()
            _themeColors.value = dto.toThemeColors()
        }
    }
}
