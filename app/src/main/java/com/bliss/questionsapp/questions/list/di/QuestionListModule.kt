package com.bliss.questionsapp.questions.list.di

import com.bliss.questionsapp.questions.list.viewmodel.QuestionListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val questionListModule = module {
    viewModel { QuestionListViewModel(get()) }
}