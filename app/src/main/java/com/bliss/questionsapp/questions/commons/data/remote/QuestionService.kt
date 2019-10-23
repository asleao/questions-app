package com.bliss.questionsapp.questions.commons.data.remote

import com.bliss.questionsapp.questions.commons.model.QuestionResponse
import com.bliss.questionsapp.questions.commons.model.ShareResponse
import retrofit2.Response
import retrofit2.http.*

interface QuestionService {
    @GET("questions/{question_id}")
    suspend fun retrieveQuestion(@Path("question_id") questionId: Int): Response<QuestionResponse>

    @PUT("questions/{question_id}")
    suspend fun updateVotesOfQuestion(
        @Path("question_id") questionId: Int,
        @Body question: QuestionResponse
    ): Response<QuestionResponse>

    @POST("share")
    suspend fun shareQuestion(
        @Query("destination_email") destinationEmail: String,
        @Query("content_url") contentUrl: String
    ): Response<ShareResponse>
}