package com.bliss.questionsapp.questions.commons.data.remote

import com.bliss.questionsapp.questions.commons.model.QuestionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface QuestionService {
    @GET("questions/{question_id}")
    suspend fun retrieveQuestion(@Path("question_id") questionId: Int): Response<QuestionResponse>

    @PUT("questions/{question_id}")
    suspend fun updateVotesOfQuestion(
        @Path("question_id") questionId: Int,
        @Body question: QuestionResponse
    ): Response<QuestionResponse>
}