package com.bliss.questionsapp.questions.commons.data.remote

import com.bliss.questionsapp.core.network.retrofit.ServiceGenerator
import com.bliss.questionsapp.core.network.retrofit.model.Resource
import com.bliss.questionsapp.core.network.retrofit.model.RetrofitResponse
import com.bliss.questionsapp.questions.commons.model.QuestionResponse

class QuestionRemoteDataSourceImpl :
    QuestionRemoteDataSource {

    private val questionService: QuestionService by lazy {
        ServiceGenerator.createService(QuestionService::class.java)
    }

    override suspend fun retrieveQuestion(questionId: Int): Resource<QuestionResponse> {
        return RetrofitResponse { questionService.retrieveQuestion(questionId) }.result()
    }


}