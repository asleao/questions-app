package com.bliss.questionsapp.questions.healthcheck.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HealthCheckViewModel : ViewModel() {
    val loading = MutableLiveData<Boolean>()

    init {
        loading.value = true
    }
}
