package com.bliss.questionsapp.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bliss.questionsapp.core.network.retrofit.model.Error

abstract class BaseViewModel : ViewModel() {
    val loading = MutableLiveData<Boolean>(false)
    val hasConnectionProblems = MutableLiveData<Boolean>(false)
    val errorMessage = MutableLiveData<String>()
    protected val _error = MutableLiveData<Error>()
    val error: LiveData<Error>
        get() = _error

    fun showLoading(status: Boolean) {
        loading.value = status
    }

    fun hasConnectionProblems(status: Boolean) {
        hasConnectionProblems.value = status
    }

    fun changeErrorMessage(message: String) {
        errorMessage.value = message
    }

    abstract fun tryAgain()
}