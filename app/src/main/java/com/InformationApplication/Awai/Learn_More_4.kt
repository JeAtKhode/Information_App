package com.InformationApplication.Awai

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

// Fragment for the "Legal Information for Guardians" section
class Learn_More_4 : Fragment(R.layout.fragment_legal_gardian) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Show the action bar when this fragment is visible
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }
}
