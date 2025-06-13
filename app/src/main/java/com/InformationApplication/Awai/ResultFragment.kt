package com.InformationApplication.Awai

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class ResultFragment : Fragment() {

    private lateinit var scoreText: TextView
    private lateinit var feedbackText: TextView
    private lateinit var tryAgainButton: Button
    private lateinit var homeButton: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Bind UI elements
        scoreText = view.findViewById(R.id.score_text)
        feedbackText = view.findViewById(R.id.feedback_text)
        tryAgainButton = view.findViewById(R.id.retry_button)
        homeButton = view.findViewById(R.id.home_button)

        // Get score and total from fragment arguments
        val score = arguments?.getInt(getString(R.string.score)) ?: 0
        val total = arguments?.getInt(getString(R.string.total)) ?: 10

        // Display score
        scoreText.text = getString(R.string.youscored) + " "+ score + " " + getString(R.string.outof) + " " + total

        // Show feedback based on performance
        val percentage = (score * 100.0) / total
        feedbackText.text = when {
            percentage >= 90 -> getString(R.string.nity)
            percentage >= 70 -> getString(R.string.senty)
            percentage >= 50 -> getString(R.string.fity)
            else -> getString(R.string.fail)
        }

        tryAgainButton.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment2_to_quizFragment2)
        }
        homeButton.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment2_to_homeFragment)
        }


    }
}
