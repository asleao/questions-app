package com.bliss.questionsapp.core.utils

import androidx.lifecycle.MutableLiveData
import com.bliss.questionsapp.core.network.retrofit.model.Error
import com.bliss.questionsapp.core.network.retrofit.model.Resource

fun <T> Resource<T>.validateResponse(success: MutableLiveData<T>, error: MutableLiveData<Error>) {
    when (this.status) {
        Resource.Status.SUCCESS -> {
            this.data?.let {
                success.value = it
            }
        }
        else -> this.error?.let {
            error.value = it
        }
    }
}