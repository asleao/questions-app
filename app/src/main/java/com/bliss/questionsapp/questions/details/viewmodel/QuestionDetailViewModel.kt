package com.bliss.questionsapp.questions.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bliss.questionsapp.core.base.BaseViewModel
import com.bliss.questionsapp.core.utils.validateResponse
import com.bliss.questionsapp.questions.commons.data.QuestionRepository
import com.bliss.questionsapp.questions.commons.model.Choice
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

    fun retrieveQuestion(questionId: Int) {
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

    fun updateVotes(choice: Choice) {
        val choices = question.value?.choices?.map { currentChoice ->
            if (currentChoice.title == choice.title) {
                choice
            } else {
                currentChoice
            }
        }

        choices?.let {
            val question = question.value?.copy(choices = choices)
            question?.let {
                showLoading(true)
                viewModelScope.launch {
                    val resource = questionRepository.updateVotesOfQuestion(question)
                    showLoading(false)
                    resource.validateResponse(_question, _error)
                }
            }
        }

    }
}
