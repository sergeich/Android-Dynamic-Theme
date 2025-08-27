package me.sergeich.dynamictheme.xml

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

import kotlinx.coroutines.launch

class ThemeViewModel(application: Application) : AndroidViewModel(application) {

    private val _themeColors = MutableSharedFlow<ThemeColors?>()
    val themeColors: Flow<ThemeColors?> = _themeColors

    fun loadTheme() {
        viewModelScope.launch {
            try {
                val dto = ThemeRepository.fetchThemeColors()
                _themeColors.emit(dto.toThemeColors())
            } catch (e: Exception) {
                _themeColors.emit(null)
            }
        }
    }
}
