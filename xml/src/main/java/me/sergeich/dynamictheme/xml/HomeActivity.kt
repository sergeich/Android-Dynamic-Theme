package me.sergeich.dynamictheme.xml

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.LayoutInflaterCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import me.sergeich.dynamictheme.xml.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: ThemeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        val theme = intent.getParcelableExtra<ThemeColors>(INTENT_KEY_THEME)
        val originalFactory = layoutInflater.factory2
        val factory = ThemingFactory(originalFactory, theme) { parent, name, context, attrs ->
            this.delegate.createView(parent, name, context, attrs)
        }
        LayoutInflaterCompat.setFactory2(layoutInflater, factory)

        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[ThemeViewModel::class.java]
        lifecycleScope.launch {
            viewModel.themeColors.distinctUntilChanged().collect { theme ->
                if (theme != null) {
                    intent.putExtra(INTENT_KEY_THEME, theme)
                    runOnUiThread { recreate() }
                }
            }
        }

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_home)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { _ ->
            viewModel.loadTheme()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private companion object {
        private const val INTENT_KEY_THEME =
            "me.sergeich.dynamictheming.HomeActivity.INTENT_KEY_THEME"
    }
}
