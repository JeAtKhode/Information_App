package com.InformationApplication.Awai

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

// Fragment that displays the About Kauri screen
class AboutKauriFragment : Fragment(R.layout.fragment_about_kauri) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Show the action bar when this fragment is visible
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }
}
