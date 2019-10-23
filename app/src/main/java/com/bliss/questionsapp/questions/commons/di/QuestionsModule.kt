package com.bliss.questionsapp.questions.commons.di

import com.bliss.questionsapp.questions.commons.data.QuestionRepository
import com.bliss.questionsapp.questions.commons.data.QuestionRepositoryImpl
import com.bliss.questionsapp.questions.commons.data.remote.QuestionRemoteDataSource
import com.bliss.questionsapp.questions.commons.data.remote.QuestionRemoteDataSourceImpl
import com.bliss.questionsapp.questions.details.di.questionDetailModule
import com.bliss.questionsapp.questions.healthcheck.di.healthCheckModule
import com.bliss.questionsapp.questions.list.di.questionListModule
import com.bliss.questionsapp.questions.share.di.questionShareModule
import org.koin.dsl.module

val questionCommonModules = module {
    factory { QuestionRemoteDataSourceImpl() as QuestionRemoteDataSource }
    factory { QuestionRepositoryImpl(get()) as QuestionRepository }
}
val questionsModules =
    listOf(
        healthCheckModule,
        questionListModule,
        questionCommonModules,
        questionDetailModule,
        questionShareModule
    )