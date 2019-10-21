package com.bliss.questionsapp.questions.healthcheck.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bliss.questionsapp.core.network.retrofit.model.Error
import com.bliss.questionsapp.core.utils.validateResponse
import com.bliss.questionsapp.questions.healthcheck.data.HealthRepository
import com.bliss.questionsapp.questions.healthcheck.model.HealthResponse
import kotlinx.coroutines.launch

class HealthCheckViewModel(private val healthRepository: HealthRepository) : ViewModel() {
    val loading = MutableLiveData<Boolean>()
    val hasConnectionProblems = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    private val _error = MutableLiveData<Error>()
    val error: LiveData<Error>
        get() = _error
    private val _health = MutableLiveData<HealthResponse>()
    val health: LiveData<HealthResponse>
        get() = _health

    init {
        showLoading(true)
        checkHealth()
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

    private fun checkHealth() {
        viewModelScope.launch {
            val resource = healthRepository.checkHealthStatus()
            resource.validateResponse(_health, _error)
        }
    }

    fun tryAgain() {
        checkHealth()
    }
}
