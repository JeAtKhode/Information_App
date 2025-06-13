package com.InformationApplication.Awai

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

class QuizFragment : Fragment() {

    private lateinit var questions: Array<String>
    private val answers = booleanArrayOf(
        true, false, true, false, true,
        true, true, true, true, true
    )

    private var currentQuestion = 0
    private var score = 0

    private lateinit var questionText: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var backButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Load questions
        questions = arrayOf(
            getString(R.string.question1),
            getString(R.string.question2),
            getString(R.string.question3),
            getString(R.string.question4),
            getString(R.string.question5),
            getString(R.string.question6),
            getString(R.string.question7),
            getString(R.string.question8),
            getString(R.string.question9),
            getString(R.string.question10)
        )

        // Bind UI components
        questionText = view.findViewById(R.id.question_text)
        trueButton = view.findViewById(R.id.button_true)
        falseButton = view.findViewById(R.id.button_false)
        nextButton = view.findViewById(R.id.btn_next)
        backButton = view.findViewById(R.id.btn_back)

        showQuestion()

        // Answer buttons
        trueButton.setOnClickListener { checkAnswer(true) }
        falseButton.setOnClickListener { checkAnswer(false) }

        // Next manually moves forward
        nextButton.setOnClickListener {
            if (currentQuestion < questions.size - 1) {
                currentQuestion++
                showQuestion()
            } else {
                Toast.makeText(requireContext(), getString(R.string.lastquest), Toast.LENGTH_SHORT).show()
            }
        }

        // Back moves backward
        backButton.setOnClickListener {
            if (currentQuestion > 0) {
                currentQuestion--
                showQuestion()
            } else {
                Toast.makeText(requireContext(), getString(R.string.firstquest), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showQuestion() {
        if (currentQuestion < questions.size) {
            val formatted = getString(R.string.question_label, currentQuestion + 1, questions[currentQuestion])
            questionText.text = formatted
        }
    }

    private fun checkAnswer(userAnswer: Boolean) {
        if (currentQuestion >= questions.size) return

        if (answers[currentQuestion] == userAnswer) {
            score++
            Snackbar.make(requireView(), getString(R.string.correct), Snackbar.LENGTH_SHORT).show()
        } else {
            Snackbar.make(requireView(), getString(R.string.incorrect), Snackbar.LENGTH_SHORT).show()
        }

        if (currentQuestion == questions.size - 1) {
            // All questions done → navigate to ResultFragment
            val bundle = Bundle().apply {
                putInt(getString(R.string.score), score)
                putInt(getString(R.string.total), questions.size)
            }
            findNavController().navigate(R.id.action_quizFragment2_to_resultFragment22, bundle)
        } else {
            currentQuestion++
            showQuestion()
        }
    }
}