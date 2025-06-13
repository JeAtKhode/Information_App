package com.InformationApplication.Awai

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Show the action bar on the home screen
        (requireActivity() as AppCompatActivity).supportActionBar?.show()

        // --- Toggle Sections (Expandable Content) ---

        // Learn Section
        val toggleLearn = view.findViewById<TextView>(R.id.toggleLearn)
        val learnContent = view.findViewById<LinearLayout>(R.id.learnContent)
        toggleLearn.setOnClickListener { toggleVisibility(learnContent) }

        // Process Section
        val toggleProcess = view.findViewById<TextView>(R.id.toggleProcess)
        val processContent = view.findViewById<LinearLayout>(R.id.processContent)
        toggleProcess.setOnClickListener { toggleVisibility(processContent) }

        // Parents Section
        val toggleParents = view.findViewById<TextView>(R.id.toggleParents)
        val parentsContent = view.findViewById<LinearLayout>(R.id.parentsContent)
        toggleParents.setOnClickListener { toggleVisibility(parentsContent) }

        // Caregivers Section
        val toggleCaregivers = view.findViewById<TextView>(R.id.toggleCaregivers)
        val caregiversContent = view.findViewById<LinearLayout>(R.id.caregiversContent)
        toggleCaregivers.setOnClickListener { toggleVisibility(caregiversContent) }

        // --- Navigation Buttons (Learn More + Quiz) ---

        learnMoreBtnEscorting.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToLearnMore1()
            findNavController().navigate(action)
        }

        learnMoreBtnProcess.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToLearnMore2()
            findNavController().navigate(action)
        }

        learnMoreBtnParents.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToLearnMore3()
            findNavController().navigate(action)
        }

        learnMoreBtnCaregiver.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToLearnMore4()
            findNavController().navigate(action)
        }

        btnQuizWrapper.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToQuizFragment2()
            findNavController().navigate(action)
        }
    }

    // Helper to toggle visibility of a section
    private fun toggleVisibility(content: LinearLayout) {
        content.visibility = if (content.visibility == View.GONE) View.VISIBLE else View.GONE
    }
}
