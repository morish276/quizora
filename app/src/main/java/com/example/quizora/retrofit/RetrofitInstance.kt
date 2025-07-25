package com.example.quizora.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Singleton class for creating and providing the Retrofit instance
class RetrofitInstance {

    // Base URL of the backend API (You should externalize this in production)
    val baseurl = "http://YOUR_API_ENDPOINT/quizora/"

    // Returns a configured Retrofit instance with Gson converter
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create()) // Converts JSON to Kotlin objects
            .build()
    }
}
