package com.example.quizora.model

// A custom list class that extends ArrayList to hold multiple Question objects
// This makes it easier to work with Retrofit and Gson for deserializing API responses
class QuestionsList : ArrayList<Question>()
