package com.bliss.questionsapp.questions.healthcheck.di

import com.bliss.questionsapp.questions.healthcheck.viewmodel.HealthCheckViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val healthCheckModule = module {
    viewModel { HealthCheckViewModel() }

}