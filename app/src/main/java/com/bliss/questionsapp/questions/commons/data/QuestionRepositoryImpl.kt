package com.bliss.questionsapp.questions.commons.data

import com.bliss.questionsapp.core.network.retrofit.model.Resource
import com.bliss.questionsapp.questions.commons.data.remote.QuestionRemoteDataSource
import com.bliss.questionsapp.questions.commons.model.QuestionResponse

class QuestionRepositoryImpl(
    private val questionRemoteDataSource: QuestionRemoteDataSource
) : QuestionRepository {

    override suspend fun retrieveQuestion(questionId: Int): Resource<QuestionResponse> {
        return questionRemoteDataSource.retrieveQuestion(questionId)
    }

    override suspend fun updateVotesOfQuestion(question: QuestionResponse): Resource<QuestionResponse> {
        return questionRemoteDataSource.updateVotesOfQuestion(question)
    }
}