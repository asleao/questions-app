package com.bliss.questionsapp.questions.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bliss.questionsapp.core.base.BaseViewModel
import com.bliss.questionsapp.core.utils.validateResponse
import com.bliss.questionsapp.questions.commons.data.QuestionRepository
import com.bliss.questionsapp.questions.commons.model.QuestionResponse
import kotlinx.coroutines.launch

class QuestionDetailViewModel(
    private val questionRepository: QuestionRepository,
    private val questionId: Int
) : BaseViewModel() {

    private val _question = MutableLiveData<QuestionResponse>()
    val question: LiveData<QuestionResponse>
        get() = _question

    init {
        retrieveQuestion(questionId)
    }

    private fun retrieveQuestion(questionId: Int) {
        showLoading(true)
        viewModelScope.launch {
            val resource = questionRepository.retrieveQuestion(questionId)
            showLoading(false)
            resource.validateResponse(_question, _error)
        }
    }

    override fun tryAgain() {
        hasConnectionProblems(false)
        retrieveQuestion(questionId)
    }
}
