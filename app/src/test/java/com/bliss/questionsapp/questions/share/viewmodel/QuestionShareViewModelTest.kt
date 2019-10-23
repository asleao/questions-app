package com.bliss.questionsapp.questions.share.viewmodel

import com.bliss.questionsapp.InstantExecutorExtension
import com.bliss.questionsapp.questions.commons.data.QuestionRepository
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
internal class QuestionShareViewModelTest {
    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    lateinit var viewModel: QuestionShareViewModel
    private val healthRepository = mockk<QuestionRepository>()

    @BeforeAll
    @ExperimentalCoroutinesApi
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }
    @BeforeEach
    private fun setupViewModel() {
        viewModel = QuestionShareViewModel(healthRepository, 1)
        viewModel.buttonEnabled.observeForever { }
    }

    @AfterAll
    @ExperimentalCoroutinesApi
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Nested
    inner class ButtonEnabled {
        @Test
        fun `when user type a valid email, then button should be enabled`() {
            viewModel.email.value = "andre@gmail.com"

            assertThat(viewModel.buttonEnabled.value).isTrue()
        }

        @Test
        fun `when user doesn't type a valid email, then button should not be enabled`() {
            viewModel.email.value = ""

            assertThat(viewModel.buttonEnabled.value).isFalse()
        }

        @Test
        fun `when user type a invalid email, then button should not be enabled`() {
            viewModel.email.value = "a@a"

            assertThat(viewModel.buttonEnabled.value).isFalse()
        }
    }
}