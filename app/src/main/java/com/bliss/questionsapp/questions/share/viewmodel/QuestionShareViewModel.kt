package com.bliss.questionsapp.questions.share.viewmodel

import androidx.lifecycle.MutableLiveData
import com.bliss.questionsapp.core.base.BaseViewModel
import com.bliss.questionsapp.questions.commons.data.QuestionRepository

class QuestionShareViewModel(
    private val questionRepository: QuestionRepository,
    private val questionId: Int
) : BaseViewModel(){

    val email = MutableLiveData<String>()
    val errorEnabled = MutableLiveData<Boolean>()

    override fun tryAgain() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
