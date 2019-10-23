package com.bliss.questionsapp.questions.healthcheck.data

import com.bliss.questionsapp.core.network.retrofit.model.Resource
import com.bliss.questionsapp.questions.healthcheck.model.HealthResponse

interface HealthRepository {

    suspend fun checkHealthStatus(): Resource<HealthResponse>
}