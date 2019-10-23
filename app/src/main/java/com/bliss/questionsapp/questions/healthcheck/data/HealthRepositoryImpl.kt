package com.bliss.questionsapp.questions.healthcheck.data

import com.bliss.questionsapp.core.network.retrofit.model.Resource
import com.bliss.questionsapp.questions.healthcheck.data.remote.HealthRemoteDataSource
import com.bliss.questionsapp.questions.healthcheck.model.HealthResponse

class HealthRepositoryImpl(
    private val healthRemoteDataSource: HealthRemoteDataSource
) : HealthRepository {
    override suspend fun checkHealthStatus(): Resource<HealthResponse> {
        return healthRemoteDataSource.checkHealthStatus()
    }
}