package com.bliss.questionsapp.questions.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bliss.questionsapp.core.network.retrofit.model.Error
import com.bliss.questionsapp.core.utils.validateResponse
import com.bliss.questionsapp.questions.commons.data.QuestionRepository
import com.bliss.questionsapp.questions.commons.model.QuestionResponse
import kotlinx.coroutines.launch

class QuestionDetailViewModel(
    private val questionRepository: QuestionRepository,
    private val questionId: Int
) : ViewModel() {
    val loading = MutableLiveData<Boolean>(false)
    val hasConnectionProblems = MutableLiveData<Boolean>(false)
    val errorMessage = MutableLiveData<String>()
    private val _error = MutableLiveData<Error>()
    val error: LiveData<Error>
        get() = _error
    private val _question = MutableLiveData<QuestionResponse>()
    val question: LiveData<QuestionResponse>
        get() = _question

    init {
        retrieveQuestion(questionId)
    }

    fun showLoading(status: Boolean) {
        loading.value = status
    }

    fun hasConnectionProblems(status: Boolean) {
        hasConnectionProblems.value = status
    }

    fun changeErrorMessage(message: String) {
        errorMessage.value = message
    }

    private fun retrieveQuestion(questionId: Int) {
        viewModelScope.launch {
            val resource = questionRepository.retrieveQuestion(questionId)
            resource.validateResponse(_question, _error)
        }
    }
}
