package com.rsstudio.flobiz.data.network.repository

import com.rsstudio.flobiz.data.network.apis.QuestionApiInterface
import javax.inject.Inject

class MainRepository
@Inject
constructor(private val api: QuestionApiInterface)
{
    suspend fun getQuestionData()  = api.getAskedQuestions()
}