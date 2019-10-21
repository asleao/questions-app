package com.bliss.questionsapp.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bliss.questionsapp.core.network.retrofit.model.Error

abstract class BaseViewModel : ViewModel(), BaseViewModelContract {
    val loading = MutableLiveData<Boolean>(false)
    val hasConnectionProblems = MutableLiveData<Boolean>(false)
    val errorMessage = MutableLiveData<String>()
    protected val _error = MutableLiveData<Error>()
    val error: LiveData<Error>
        get() = _error

    override fun showLoading(status: Boolean) {
        loading.value = status
    }

    override fun hasConnectionProblems(status: Boolean) {
        hasConnectionProblems.value = status
    }

    override fun changeErrorMessage(message: String) {
        errorMessage.value = message
    }

    abstract override fun tryAgain()
}