package com.bliss.questionsapp.questions.healthcheck.di

import com.bliss.questionsapp.questions.healthcheck.data.HealthRepository
import com.bliss.questionsapp.questions.healthcheck.data.HealthRepositoryImpl
import com.bliss.questionsapp.questions.healthcheck.data.remote.HealthRemoteDataSource
import com.bliss.questionsapp.questions.healthcheck.data.remote.HealthRemoteDataSourceImpl
import com.bliss.questionsapp.questions.healthcheck.viewmodel.HealthCheckViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val healthCheckModule = module {
    factory { HealthRemoteDataSourceImpl() as HealthRemoteDataSource }
    factory { HealthRepositoryImpl(get()) as HealthRepository }
    viewModel { HealthCheckViewModel(get()) }
}