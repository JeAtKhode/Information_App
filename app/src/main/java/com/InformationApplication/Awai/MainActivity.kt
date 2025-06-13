package com.InformationApplication.Awai

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.drawer_layout
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the locale before inflating any views
        val langCode = getSharedPreferences("app_settings", MODE_PRIVATE)
            .getString("lang", "en") ?: "en"
        setLocale(langCode)

        setContentView(R.layout.activity_main)

        // Setup NavController and Navigation Drawer
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        val navView = findViewById<NavigationView>(R.id.nav_view)
        navView.itemIconTintList = ContextCompat.getColorStateList(this, R.color.white)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        // Optional: Add footer to nav drawer
        val footerView = layoutInflater.inflate(R.layout.nav_drawer_footer, navView, false)
        navView.addView(footerView)

        // Handle navigation drawer item clicks
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.language -> toggleLanguage()
                R.id.homeFragment -> navController.navigate(R.id.homeFragment)
                R.id.aboutus -> navController.navigate(R.id.aboutus)
                R.id.aboutkauri -> navController.navigate(R.id.aboutkauri)
                R.id.orangaTamariki -> {
                    val browserIntent = Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.orangatamariki.govt.nz/"))
                    startActivity(browserIntent)
                }
                R.id.masseyUniversity -> {
                    val browserIntent = Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.massey.ac.nz/"))
                    startActivity(browserIntent)
                }
                R.id.emergencyHotline -> {
                    val dialIntent = Intent(Intent.ACTION_DIAL)
                    dialIntent.data = Uri.parse("tel:111")
                    startActivity(dialIntent)
                }
            }

            drawerLayout.closeDrawer(GravityCompat.END)
            true
        }

        // Configure top-level destinations for the ActionBar
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.aboutus, R.id.aboutkauri)
        )

        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    // Inflate menu in ActionBar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar_menu, menu)
        return true
    }

    // Handle ActionBar item clicks (e.g., open drawer)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_drawer -> {
                drawer_layout.openDrawer(GravityCompat.END)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Handle "up" navigation from fragments
    override fun onSupportNavigateUp(): Boolean {
        findNavController(R.id.nav_host_fragment).navigate(R.id.homeFragment)
        return true
    }

    // Switch between English and Te Reo Māori
    private fun toggleLanguage() {
        val sharedPrefs = getSharedPreferences("app_settings", MODE_PRIVATE)
        val currentLang = sharedPrefs.getString("lang", "en") ?: "en"
        val newLang = if (currentLang == "en") "mi" else "en"

        sharedPrefs.edit().putString("lang", newLang).apply()
        setLocale(newLang)

        Toast.makeText(
            this,
            getString(R.string.langToast),
            Toast.LENGTH_SHORT
        ).show()

        recreate() // Refresh the UI with new locale
    }

    // Apply new locale settings
    private fun setLocale(langCode: String) {
        val locale = Locale(langCode)
        Locale.setDefault(locale)

        val config = resources.configuration
        config.setLocale(locale)
        config.setLayoutDirection(locale)

        @Suppress("DEPRECATION")
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}
