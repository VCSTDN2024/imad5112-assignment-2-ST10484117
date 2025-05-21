// QuizActivity.kt
package com.example.flashcardapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import vcmsa.ci.flashcardapp.R

class QuizActivity : AppCompatActivity() {

    private lateinit var questionText: TextView // Displays the current quiz question
    private lateinit var trueButton: Button  // Button for "True" answer
    private lateinit var falseButton: Button  // Button for "False" answer
    private lateinit var nextButton: Button // Button to move to the next question
    private lateinit var feedbackText: TextView // Displays feedback (Correct/Incorrect)

    // Array to store the quiz questions (True/False statements)
    private val questions = arrayOf(
        "Nelson Mandela was the president of South Africa in 1994.",
        "The first man to step on the moon was Neil Armstrong.",
        "The capital of France is London.",
        "Mount Everest is the tallest mountain in the world.",
        "The Earth is flat."
    )

    // Array to store the correct answers corresponding to each question
    private val answers = booleanArrayOf(true, true, false, true, false)
    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionText = findViewById(R.id.questionText)
        trueButton = findViewById(R.id.trueButton)
        falseButton = findViewById(R.id.falseButton)
        nextButton = findViewById(R.id.nextButton)
        feedbackText = findViewById(R.id.feedbackText)

        // Load the first question when the activity starts
        loadQuestion()

        // Set up click listener for the "True" button
        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            nextQuestion()
        }
    }

    private fun loadQuestion() {
        // Check if there are more questions to display
        if (currentQuestionIndex < questions.size) {
            questionText.text = questions[currentQuestionIndex]
            feedbackText.text = "" // Clear previous feedback
        } else {
            finishQuiz()
        }
    }

    private fun checkAnswer(userAnswer: Boolean) {
        if (userAnswer == answers[currentQuestionIndex]) {
            feedbackText.text = "Correct!"
            score++
        } else {
            feedbackText.text = "Incorrect!"
        }
    }

    private fun nextQuestion() {
        currentQuestionIndex++
        if (currentQuestionIndex < questions.size) {
            loadQuestion()
        } else {
            finishQuiz()
        }
    }

    private fun finishQuiz() {
        val intent = Intent(this, ScoreActivity::class.java)
        intent.putExtra("score", score)
        intent.putExtra("questions", questions)
        intent.putExtra("answers", answers)
        startActivity(intent)
        finish()
    }
}