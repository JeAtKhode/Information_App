package com.InformationApplication.Awai

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import java.util.*

class DisclaimerFragment : Fragment(R.layout.fragment_disclaimer) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Hide the action bar on this screen
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        // Get saved language preference or default to English
        val langCode = requireActivity()
            .getSharedPreferences("app_settings", AppCompatActivity.MODE_PRIVATE)
            .getString("lang", "en") ?: "en"
        setLocale(langCode)

        // Set up button listeners
        val acceptButton = view.findViewById<Button>(R.id.accept_button)
        val changeLangButton = view.findViewById<Button>(R.id.change_language)

        // Navigate to HomeFragment when disclaimer is accepted
        acceptButton.setOnClickListener {
            findNavController().navigate(R.id.action_disclaimerFragment_to_homeFragment)
        }

        // Toggle language when the button is pressed
        changeLangButton.setOnClickListener {
            toggleLanguage()
        }
    }

    // Switch between English and Māori, save preference, reload UI
    private fun toggleLanguage() {
        val sharedPrefs = requireActivity()
            .getSharedPreferences("app_settings", AppCompatActivity.MODE_PRIVATE)
        val currentLang = sharedPrefs.getString("lang", "en") ?: "en"
        val newLang = if (currentLang == "en") "mi" else "en"

        sharedPrefs.edit().putString("lang", newLang).apply()
        setLocale(newLang)

        Toast.makeText(
            requireContext(),
            getString(R.string.langToast),
            Toast.LENGTH_SHORT
        ).show()

        // Recreate activity to apply the new language
        requireActivity().recreate()
    }

    // Apply the selected language to the app
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
