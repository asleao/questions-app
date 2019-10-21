package com.bliss.questionsapp.questions.healthcheck.data.remote

import com.bliss.questionsapp.questions.healthcheck.model.HealthResponse
import retrofit2.Response
import retrofit2.http.GET

interface HealthService {
    @GET("health")
    suspend fun checkHealthStatus(): Response<HealthResponse>
}