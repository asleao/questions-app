package com.bliss.questionsapp.questions.healthcheck.viewmodel

import com.bliss.questionsapp.InstantExecutorExtension
import com.bliss.questionsapp.core.network.retrofit.model.Resource
import com.bliss.questionsapp.core.network.retrofit.model.Error
import com.bliss.questionsapp.questions.healthcheck.data.HealthRepository
import com.bliss.questionsapp.questions.healthcheck.model.HealthResponse
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
internal class HealthCheckViewModelTest {
    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    lateinit var viewModel: HealthCheckViewModel
    private val healthRepository = mockk<HealthRepository>()

    @BeforeAll
    @ExperimentalCoroutinesApi
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    private fun setupViewModel() {
        viewModel = HealthCheckViewModel(healthRepository)
    }

    @AfterAll
    @ExperimentalCoroutinesApi
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Nested
    inner class CheckHealth {
        @Test
        fun `when request is sucessfull, then health livedata should be filled`() {
            every { runBlocking { healthRepository.checkHealthStatus() } } answers {
                Resource.success(HealthResponse("OK"))
            }
            setupViewModel()
            viewModel.checkHealth()

            assertThat(viewModel.loading.value).isTrue()
            assertThat(viewModel.health.value).isNotNull()
            assertThat(viewModel.error.value).isNull()
        }

        @Test
        fun `when request is not sucessfull, then error livedata should be filled`() {
            every { runBlocking { healthRepository.checkHealthStatus()  } } answers {
                Resource.error(Error(5, "Error", "Something went wrong"))
            }
            setupViewModel()
            viewModel.checkHealth()

            assertThat(viewModel.loading.value).isTrue()
            assertThat(viewModel.health.value).isNull()
            assertThat(viewModel.error.value).isNotNull()
        }
    }

    @Nested
    inner class TryAgain {
        @Test
        fun `when tryAgain is called, then checkHealthStatus should be called as well`() {
            every { runBlocking { healthRepository.checkHealthStatus() } } answers {
                Resource.success(HealthResponse("OK"))
            }
            setupViewModel()
            viewModel.tryAgain()

            assertThat(viewModel.loading.value).isTrue()
            assertThat(viewModel.health.value).isNotNull()
            assertThat(viewModel.error.value).isNull()
        }
    }
}