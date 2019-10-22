package com.bliss.questionsapp.questions.commons.data

import com.bliss.questionsapp.core.network.retrofit.model.Resource
import com.bliss.questionsapp.questions.commons.model.QuestionResponse

interface QuestionRepository {

    suspend fun retrieveQuestion(questionId: Int): Resource<QuestionResponse>

    suspend fun updateVotesOfQuestion(question: QuestionResponse): Resource<QuestionResponse>
}