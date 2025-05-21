// MainActivity.kt
package com.example.flashcardapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import vcmsa.ci.flashcardapp.R

class MainActivity : AppCompatActivity() {
    //Declare UI elements as lateinit variables, meaning they will be initialized later in onCreate.
    private lateinit var welcomeText: TextView
    private lateinit var startButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initialize the TextView and Button by finding them using their IDs defined in activity_main.xml.
        welcomeText = findViewById(R.id.welcomeText)
        startButton = findViewById(R.id.startButton)
        //Set the descriptive welcome text that appears on the main screen.
        welcomeText.text = "Welcome to the Flashcard Quiz App! Test your knowledge with True/False questions."
        // Set an OnClickListener for the startButton.
        // This defines what happens when the user taps the "Start Quiz" button.
        startButton.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
    }
}