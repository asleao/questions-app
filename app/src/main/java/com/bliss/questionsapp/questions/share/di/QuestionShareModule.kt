package com.bliss.questionsapp.questions.share.di

import com.bliss.questionsapp.questions.share.ui.view.QuestionShareFragment.Companion.QUESTIONSHARE_ARGUMENT
import com.bliss.questionsapp.questions.share.viewmodel.QuestionShareViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val questionShareModule = module {
    viewModel { QuestionShareViewModel(get(), getProperty(QUESTIONSHARE_ARGUMENT)) }
}