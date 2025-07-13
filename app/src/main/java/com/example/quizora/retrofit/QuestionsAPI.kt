package com.example.quizora.retrofit

import com.example.quizora.model.QuestionsList
import retrofit2.http.GET
import retrofit2.Response

// Retrofit interface that defines the API endpoints
interface QuestionsAPI {

    // Makes a GET request to fetch the list of quiz questions
    @GET("questionapi.php")
    suspend fun getQuestions(): Response<QuestionsList>
}