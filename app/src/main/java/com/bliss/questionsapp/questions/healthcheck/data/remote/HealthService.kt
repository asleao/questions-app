package com.bliss.questionsapp.questions.healthcheck.data.remote

import com.bliss.questionsapp.questions.healthcheck.model.HealthResponse
import retrofit2.Response
import retrofit2.http.POST

interface HealthService{
    @POST("health")
    suspend fun checkHealthStatus(): Response<HealthResponse>
}