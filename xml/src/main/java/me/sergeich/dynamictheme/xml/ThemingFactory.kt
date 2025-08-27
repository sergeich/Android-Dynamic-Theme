package me.sergeich.dynamictheme.xml

import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ThemingFactory(
    private val originalFactory: LayoutInflater.Factory2?,
    private val themeColors: ThemeColors?,
    private val createBlock: (parent: View?, name: String, context: Context, attrs: AttributeSet) -> View?
) : LayoutInflater.Factory2 {

    override fun onCreateView(parent: View?, name: String, context: Context, attrs: AttributeSet): View? {
        val view = createBlock(parent, name, context, attrs)
            ?: originalFactory?.onCreateView(parent, name, context, attrs)
            ?: createViewFromPlatform(name, context, attrs)

        applyTheme(view)
        return view
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return onCreateView(null, name, context, attrs)
    }

    private fun createViewFromPlatform(name: String, context: Context, attrs: AttributeSet): View? {
        return runCatching {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                LayoutInflater.from(context).createView(context, name, null, attrs)
            } else {
                LayoutInflater.from(context).createView(name, null, attrs)
            }
        }.getOrNull()
    }

    private fun applyTheme(view: View?) {
        themeColors ?: return
        when (view) {
            is Button -> {
                view.backgroundTintList = ColorStateList.valueOf(themeColors.primary)
            }
            is TextView -> {
                view.setTextColor(themeColors.text)
            }
            is FloatingActionButton -> {
                view.backgroundTintList = ColorStateList.valueOf(themeColors.secondary)
                view.imageTintList = ColorStateList.valueOf(themeColors.onPrimary)
            }
        }
    }
}
