package com.bliss.questionsapp.questions.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bliss.questionsapp.core.base.BaseViewModel
import com.bliss.questionsapp.core.utils.validateResponse
import com.bliss.questionsapp.questions.commons.data.QuestionRepository
import com.bliss.questionsapp.questions.commons.model.QuestionResponse
import kotlinx.coroutines.launch

class QuestionListViewModel(private val questionRepository: QuestionRepository) : BaseViewModel() {

    private val _questions = MutableLiveData<List<QuestionResponse>>()
    val questions: LiveData<List<QuestionResponse>>
        get() = _questions

    init {
        listAllQuestions("")
    }

    private fun listAllQuestions(query: String) {
        showLoading(true)
        viewModelScope.launch {
            val resource = questionRepository.listAllQuestions(filter = query)
            showLoading(false)
            resource.validateResponse(_questions, _error)
        }
    }


    override fun tryAgain() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
