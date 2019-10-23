package com.bliss.questionsapp.questions.share.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bliss.questionsapp.core.base.BaseViewModel
import com.bliss.questionsapp.core.utils.isEmailValid
import com.bliss.questionsapp.core.utils.validateResponse
import com.bliss.questionsapp.questions.commons.data.QuestionRepository
import com.bliss.questionsapp.questions.commons.model.ShareResponse
import kotlinx.coroutines.launch

class QuestionShareViewModel(
    private val questionRepository: QuestionRepository,
    private val questionId: Int
) : BaseViewModel(){

    val email = MutableLiveData<String>()
    val buttonEnabled = MediatorLiveData<Boolean>()

    private val _share = MutableLiveData<ShareResponse>()
    val share: LiveData<ShareResponse>
        get() = _share


    init {
        buttonEnabled.addSource(email) { email ->
            buttonEnabled.value = email.isEmailValid()
        }
    }

    fun shareQuestion() {
        showLoading(true)
        email.value?.let { email ->
            viewModelScope.launch {
                val resource = questionRepository.shareQuestion(email, "")
                showLoading(false)
                resource.validateResponse(_share, _error)
            }
        }
    }

    override fun tryAgain() {
        hasConnectionProblems(false)
        shareQuestion()
    }
}
