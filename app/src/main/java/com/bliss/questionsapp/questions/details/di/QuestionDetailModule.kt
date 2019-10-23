package com.bliss.questionsapp.questions.details.di

import com.bliss.questionsapp.questions.details.ui.view.QuestionDetailFragment.Companion.QUESTIONDETAIL_ARGUMENT
import com.bliss.questionsapp.questions.details.viewmodel.QuestionDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val questionDetailModule = module {
    viewModel { QuestionDetailViewModel(get(), getProperty(QUESTIONDETAIL_ARGUMENT)) }
}