package com.example.quizora.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.quizora.model.QuestionsList
import com.example.quizora.repository.QuizRepository

// ViewModel that provides quiz data to the UI (MainActivity) using LiveData
class QuizViewModel : ViewModel() {

    // Repository to handle data fetching logic
    var repository: QuizRepository = QuizRepository()

    // LiveData holding the list of questions
    lateinit var questionsLiveData: LiveData<QuestionsList>

    init {
        // Fetching questions as soon as ViewModel is initialized
        questionsLiveData = repository.getQuestionsFromAPI()
    }

    // Function to expose the LiveData to the UI
    fun getQuestionsFromLiveData(): LiveData<QuestionsList> {
        return questionsLiveData
    }
}
