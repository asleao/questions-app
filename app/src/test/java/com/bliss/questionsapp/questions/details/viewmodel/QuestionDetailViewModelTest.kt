package com.bliss.questionsapp.questions.details.viewmodel

import com.bliss.questionsapp.InstantExecutorExtension
import com.bliss.questionsapp.core.network.retrofit.model.Error
import com.bliss.questionsapp.core.network.retrofit.model.Resource
import com.bliss.questionsapp.questions.commons.data.QuestionRepository
import com.bliss.questionsapp.questions.commons.model.QuestionResponse
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
internal class QuestionDetailViewModelTest {
    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    lateinit var viewModel: QuestionDetailViewModel
    private val questionRepository = mockk<QuestionRepository>()

    @BeforeAll
    @ExperimentalCoroutinesApi
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    private fun setupViewModel() {
        viewModel = QuestionDetailViewModel(questionRepository, 1)
    }

    @AfterAll
    @ExperimentalCoroutinesApi
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Nested
    inner class RetrieveQuestion {
        @Test
        fun `when request is sucessfull, then question livedata should be filled`() {
            every { runBlocking { questionRepository.retrieveQuestion(1) } } answers {
                Resource.success(QuestionResponse(1, "title", "imageUrl", "thumbUrl", emptyList()))
            }
            setupViewModel()
            viewModel.retrieveQuestion(1)

            Assertions.assertThat(viewModel.loading.value).isFalse()
            Assertions.assertThat(viewModel.question.value).isNotNull()
            Assertions.assertThat(viewModel.error.value).isNull()
        }

        @Test
        fun `when request is not sucessfull, then error livedata should be filled`() {
            every { runBlocking { questionRepository.retrieveQuestion(1) } } answers {
                Resource.error(Error(5, "Error", "Something went wrong"))
            }
            setupViewModel()
            viewModel.retrieveQuestion(1)

            Assertions.assertThat(viewModel.loading.value).isFalse()
            Assertions.assertThat(viewModel.question.value).isNull()
            Assertions.assertThat(viewModel.error.value).isNotNull()
        }
    }

    @Nested
    inner class TryAgain {
        @Test
        fun `when tryAgain is called, then getReviews should be called as well`() {
            every { runBlocking { questionRepository.retrieveQuestion(1) } } answers {
                Resource.success(QuestionResponse(1, "title", "imageUrl", "thumbUrl", emptyList()))
            }
            setupViewModel()
            viewModel.tryAgain()

            Assertions.assertThat(viewModel.loading.value).isFalse()
            Assertions.assertThat(viewModel.question.value).isNotNull()
            Assertions.assertThat(viewModel.error.value).isNull()
        }
    }
}