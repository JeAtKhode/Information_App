package com.InformationApplication.Awai

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

// Fragment for the "Learn More - Escorting" section
class Learn_More_1 : Fragment(R.layout.fragment_about_escorting) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Show the action bar when this fragment is displayed
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }
}
