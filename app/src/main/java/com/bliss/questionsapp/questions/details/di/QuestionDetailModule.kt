package com.bliss.questionsapp.questions.details.di

import com.bliss.questionsapp.questions.commons.data.QuestionRepository
import com.bliss.questionsapp.questions.commons.data.QuestionRepositoryImpl
import com.bliss.questionsapp.questions.commons.data.remote.QuestionRemoteDataSource
import com.bliss.questionsapp.questions.commons.data.remote.QuestionRemoteDataSourceImpl
import com.bliss.questionsapp.questions.details.ui.view.QuestionDetailFragment.Companion.QUESTIONDETAIL_ARGUMENT
import com.bliss.questionsapp.questions.details.viewmodel.QuestionDetailViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val questionDetailModule = module {
    factory { QuestionRemoteDataSourceImpl() as QuestionRemoteDataSource }
    factory { QuestionRepositoryImpl(get()) as QuestionRepository }
    viewModel { QuestionDetailViewModel(get(), getProperty(QUESTIONDETAIL_ARGUMENT)) }
}