package com.bliss.questionsapp.questions.healthcheck.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bliss.questionsapp.core.base.BaseViewModel
import com.bliss.questionsapp.core.utils.validateResponse
import com.bliss.questionsapp.questions.healthcheck.data.HealthRepository
import com.bliss.questionsapp.questions.healthcheck.model.HealthResponse
import kotlinx.coroutines.launch

class HealthCheckViewModel(
    private val healthRepository: HealthRepository
) : BaseViewModel() {

    private val _health = MutableLiveData<HealthResponse>()
    val health: LiveData<HealthResponse>
        get() = _health

    init {
        showLoading(true)
        checkHealth()
    }

    fun checkHealth() {
        viewModelScope.launch {
            val resource = healthRepository.checkHealthStatus()
            resource.validateResponse(_health, _error)
        }
    }

    override fun tryAgain() {
        hasConnectionProblems(false)
        checkHealth()
    }
}
