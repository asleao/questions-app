package com.bliss.questionsapp.questions.commons.data

import com.bliss.questionsapp.core.network.retrofit.model.Resource
import com.bliss.questionsapp.questions.commons.data.remote.QuestionRemoteDataSource
import com.bliss.questionsapp.questions.commons.model.QuestionResponse
import com.bliss.questionsapp.questions.commons.model.ShareResponse

class QuestionRepositoryImpl(
    private val questionRemoteDataSource: QuestionRemoteDataSource
) : QuestionRepository {

    override suspend fun listAllQuestions(
        limit: Int,
        offset: Int,
        filter: String
    ): Resource<List<QuestionResponse>> {
        return questionRemoteDataSource.listAllQuestions(limit, offset, filter)
    }

    override suspend fun retrieveQuestion(questionId: Int): Resource<QuestionResponse> {
        return questionRemoteDataSource.retrieveQuestion(questionId)
    }

    override suspend fun updateVotesOfQuestion(question: QuestionResponse): Resource<QuestionResponse> {
        return questionRemoteDataSource.updateVotesOfQuestion(question)
    }

    override suspend fun shareQuestion(
        destinationEmail: String,
        contentUrl: String
    ): Resource<ShareResponse> {
        return questionRemoteDataSource.shareQuestion(destinationEmail, contentUrl)
    }
}