package com.bliss.questionsapp.questions.commons.data.remote

import com.bliss.questionsapp.core.network.retrofit.ServiceGenerator
import com.bliss.questionsapp.core.network.retrofit.model.Error
import com.bliss.questionsapp.core.network.retrofit.model.Resource
import com.bliss.questionsapp.core.network.retrofit.model.RetrofitResponse
import com.bliss.questionsapp.questions.commons.model.QuestionResponse
import com.bliss.questionsapp.questions.commons.model.ShareResponse

class QuestionRemoteDataSourceImpl : QuestionRemoteDataSource {

    private val questionService: QuestionService by lazy {
        ServiceGenerator.createService(QuestionService::class.java)
    }

    override suspend fun retrieveQuestion(questionId: Int): Resource<QuestionResponse> {
        return RetrofitResponse { questionService.retrieveQuestion(questionId) }.result()
    }

    override suspend fun updateVotesOfQuestion(question: QuestionResponse): Resource<QuestionResponse> {
        return RetrofitResponse {
            questionService.updateVotesOfQuestion(
                question.id,
                question
            )
        }.result()
    }

    override suspend fun shareQuestion(
        destinationEmail: String,
        contentUrl: String
    ): Resource<ShareResponse> {
        return RetrofitResponse {
            questionService.shareQuestion(
                destinationEmail,
                contentUrl
            )
        }.result()
    }
}