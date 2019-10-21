package com.bliss.questionsapp.questions.healthcheck.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bliss.questionsapp.questions.healthcheck.data.HealthRepository

class HealthCheckViewModel(private val healthRepository: HealthRepository) : ViewModel() {
    val loading = MutableLiveData<Boolean>()
    val hasConnectionProblems = MutableLiveData<Boolean>()
    val noConnectionTitle = MutableLiveData<String>()

    init {
        loading.value = false
        hasConnectionProblems.value = true
        noConnectionTitle.value = "Teste"
    }
}
