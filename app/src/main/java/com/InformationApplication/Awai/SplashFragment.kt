package com.InformationApplication.Awai

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

// SplashFragment displays the splash screen when the app starts
class SplashFragment : Fragment(R.layout.fragment_splash) {

    // Called after the fragment's view has been created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Hide the Action Bar to make the splash screen full screen
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        // Post a delayed task to navigate to the disclaimer screen after 2 seconds (2000 milliseconds)
        Handler(Looper.getMainLooper()).postDelayed({
            // Navigate to the DisclaimerFragment using the navigation component
            findNavController().navigate(R.id.action_splashFragment_to_disclaimerFragment)
        }, 2000)
    }
}
