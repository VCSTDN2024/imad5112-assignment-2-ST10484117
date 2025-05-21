// ScoreActivity.kt
package com.example.flashcardapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.LinearLayout
import android.widget.ScrollView
import vcmsa.ci.flashcardapp.R

class ScoreActivity : AppCompatActivity() {

    private lateinit var scoreText: TextView
    private lateinit var feedbackText: TextView
    private lateinit var reviewButton: Button
    private lateinit var exitButton: Button
    private lateinit var reviewLayout: LinearLayout
    private lateinit var reviewScrollView: ScrollView

    private var score = 0
    private lateinit var questions: Array<String>
    private lateinit var answers: BooleanArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        scoreText = findViewById(R.id.scoreText)
        feedbackText = findViewById(R.id.feedbackText)
        reviewButton = findViewById(R.id.reviewButton)
        exitButton = findViewById(R.id.exitButton)
        reviewLayout = findViewById(R.id.reviewLayout)
        reviewScrollView = findViewById(R.id.reviewScrollView)

        score = intent.getIntExtra("score", 0)
        questions = intent.getStringArrayExtra("questions") ?: emptyArray()
        answers = intent.getBooleanArrayExtra("answers") ?: booleanArrayOf()

        scoreText.text = "Your Score: $score / ${questions.size}"
        feedbackText.text = if (score >= 3) "Great job!" else "Keep practicing!"

        reviewButton.setOnClickListener {
            showReview()
            reviewButton.visibility = Button.GONE
            reviewScrollView.visibility = ScrollView.VISIBLE
        }

        exitButton.setOnClickListener {
            finishAffinity() // exit the app
        }

        reviewScrollView.visibility = ScrollView.GONE
    }

    private fun showReview() {
        for (i in questions.indices) {
            val questionTextView = TextView(this)
            questionTextView.text = questions[i]
            val answerTextView = TextView(this)
            answerTextView.text = "Correct Answer: ${answers[i]}"
            reviewLayout.addView(questionTextView)
            reviewLayout.addView(answerTextView)
        }
    }
}