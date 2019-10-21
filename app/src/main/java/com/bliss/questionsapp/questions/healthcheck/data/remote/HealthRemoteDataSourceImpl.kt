package com.bliss.questionsapp.questions.healthcheck.data.remote

import com.bliss.questionsapp.core.network.retrofit.ServiceGenerator
import com.bliss.questionsapp.core.network.retrofit.model.Resource
import com.bliss.questionsapp.core.network.retrofit.model.RetrofitResponse
import com.bliss.questionsapp.questions.healthcheck.model.HealthResponse

class HealthRemoteDataSourceImpl : HealthRemoteDataSource {

    private val healthService: HealthService by lazy {
        ServiceGenerator.createService(HealthService::class.java)
    }

    override suspend fun checkHealthStatus(): Resource<HealthResponse> {
        return RetrofitResponse { healthService.checkHealthStatus() }.result()
    }
}