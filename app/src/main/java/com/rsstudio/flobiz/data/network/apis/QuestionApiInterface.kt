package com.rsstudio.flobiz.data.network.apis

import com.rsstudio.flobiz.data.network.model.Question
import retrofit2.http.GET

interface QuestionApiInterface {

    @GET("2.2/questions?key=ZiXCZbWaOwnDgpVT9Hx8IA((&order=desc&sort=activity&site=stackoverflow")
    suspend fun getAskedQuestions(
    ): Question
}