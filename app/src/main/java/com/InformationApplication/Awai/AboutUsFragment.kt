package com.InformationApplication.Awai

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

// Fragment for the "About Us" screen
class AboutUsFragment : Fragment(R.layout.fragment_about_us) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Show the action bar when this fragment is displayed
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }
}
