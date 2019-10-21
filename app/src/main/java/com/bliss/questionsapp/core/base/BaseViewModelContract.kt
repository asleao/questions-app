package com.bliss.questionsapp.core.base

interface BaseViewModelContract {
    fun showLoading(status: Boolean)
    fun hasConnectionProblems(status: Boolean)
    fun changeErrorMessage(message: String)
    fun tryAgain()
}