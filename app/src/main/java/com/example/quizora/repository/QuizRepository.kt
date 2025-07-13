package com.example.quizora.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quizora.model.QuestionsList
import com.example.quizora.retrofit.QuestionsAPI
import com.example.quizora.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// Repository class responsible for fetching questions from the API
class QuizRepository {

    // Retrofit interface instance
    var questionsAPI: QuestionsAPI

    init {
        // Initializing Retrofit and creating the API service instance
        questionsAPI = RetrofitInstance().getRetrofitInstance().create(QuestionsAPI::class.java)
    }

    // Function to fetch quiz questions using LiveData for UI observation
    fun getQuestionsFromAPI(): LiveData<QuestionsList> {
        val data = MutableLiveData<QuestionsList>()

        GlobalScope.launch(Dispatchers.IO) {
            // Call the API to get questions
            val response = questionsAPI.getQuestions()

            if (response != null) {
                // Post the received data to LiveData
                data.postValue(response.body())
                Log.i("TAGY", "Fetched questions: ${data.value}")
            }
        }

        return data
    }
}