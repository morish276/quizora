package com.example.quizora.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.quizora.R
import com.example.quizora.databinding.ActivityResultBinding

// Activity to display the user's quiz result
class ResultActivity : AppCompatActivity() {

    // ViewBinding for the layout
    lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Set up data binding with result layout
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result)

        // Show the result score using static values from MainActivity
        binding.tvAnswer.text = "Your Score: ${MainActivity.result} / ${MainActivity.totalQuestions}"

        // On clicking "Back", return to quiz start
        binding.btnBack.setOnClickListener {
            val intent = Intent(this@ResultActivity, MainActivity::class.java)
            startActivity(intent)
        }

        // Apply system window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}