package com.example.quizora.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.quizora.R
import com.example.quizora.databinding.ActivityMainBinding
import com.example.quizora.model.Question
import com.example.quizora.viewmodel.QuizViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    // ViewBinding instance
    lateinit var binding: ActivityMainBinding

    // ViewModel to access questions
    lateinit var quizViewModel: QuizViewModel

    // List to store fetched quiz questions
    lateinit var questionsList: List<Question>

    companion object {
        var result = 0             // Tracks total correct answers
        var totalQuestions = 0     // Total number of questions
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Set up data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Reset result counters at the start of the quiz
        result = 0
        totalQuestions = 0

        // Initialize ViewModel
        quizViewModel = ViewModelProvider(this).get(QuizViewModel::class.java)

        // Coroutine to observe LiveData and load the first question
        GlobalScope.launch(Dispatchers.Main) {
            quizViewModel.getQuestionsFromLiveData().observe(this@MainActivity, Observer {
                if (it.isNotEmpty()) {
                    questionsList = it
                    Log.i("TAGY", "This is the 1st question: ${questionsList[0]}")

                    // Set question and options to UI
                    binding.apply {
                        tvQuestion.text = "Q1. " + questionsList[0].question
                        radio1.text = questionsList[0].option1
                        radio2.text = questionsList[0].option2
                        radio3.text = questionsList[0].option3
                        radio4.text = questionsList[0].option4
                    }
                }
            })
        }

        var i = 1 // Index to track current question number

        binding.apply {
            btnNext.setOnClickListener {

                // Get selected option
                val selectedOption = radioGrp.checkedRadioButtonId

                if (selectedOption != -1) {
                    val redbutton = findViewById<View>(selectedOption) as RadioButton

                    questionsList.let {
                        if (i < it.size) {
                            totalQuestions = it.size

                            // Check if selected option is correct
                            if (redbutton.text.toString() == it[i - 1].correct_option) {
                                result++
                                tvResult.text = "Correct Answer: $result"
                            } else {
                                tvResult.text = "Correct Answer : $result"
                            }

                            // Show next question
                            tvQuestion.text = "Q${i + 1}. " + it[i].question
                            radio1.text = it[i].option1
                            radio2.text = it[i].option2
                            radio3.text = it[i].option3
                            radio4.text = it[i].option4

                            // If it's the last question, change button to "SUBMIT"
                            if (i == it.size - 1) {
                                btnNext.text = "SUBMIT"
                                btnNext.setBackgroundColor(resources.getColor(R.color.ans_color, null))
                            }

                            radioGrp.clearCheck()
                            i++
                        } else {
                            // Final check for last question
                            if (redbutton.text.toString() == it[i - 1].correct_option) {
                                result++
                                tvResult.text = "Correct Answer : $result"
                            }

                            // Go to ResultActivity
                            val intent = Intent(this@MainActivity, ResultActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Please select One Option", Toast.LENGTH_LONG).show()
                }
            }
        }

        // Apply system window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}