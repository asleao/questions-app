package com.bliss.questionsapp.core.utils

import androidx.lifecycle.MutableLiveData
import com.bliss.questionsapp.InstantExecutorExtension
import com.bliss.questionsapp.core.network.retrofit.model.Error
import com.bliss.questionsapp.core.network.retrofit.model.Resource
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
internal class ResourceExtensionsTest {

    lateinit var success: MutableLiveData<Boolean>
    lateinit var error: MutableLiveData<Error>
    @BeforeEach
    fun setupBeforeEach() {
        success = MutableLiveData()
        error = MutableLiveData()
    }

    @Nested
    inner class ValidateResponse {
        @Test
        fun `when is valid, variable success should be filled`() {
            val resource = Resource.success(true)
            resource.validateResponse(success, error)
            assertThat(success.value).isTrue()
            assertThat(error.value).isNull()
        }

        @Test
        fun `when is not valid, variable error should be filled`() {
            val resource = Resource.error<Boolean>(Error(-1, "", ""))
            resource.validateResponse(success, error)
            assertThat(success.value).isNull()
            assertThat(error.value).isNotNull()
        }

        @Test
        fun `when is valid and data atribute is null, then success livedata should not be filled`() {
            val resource = mockk<Resource<Boolean>>()
            every { resource.status } answers { Resource.Status.SUCCESS }
            every { resource.data } answers { null }

            resource.validateResponse(success, error)

            assertThat(resource.data).isNull()
            assertThat(error.value).isNull()
        }

        @Test
        fun `when is not valid and error atribute is null, then error livedata should not be filled`() {
            val resource = mockk<Resource<Boolean>>()
            every { resource.status } answers { Resource.Status.ERROR }
            every { resource.error } answers { null }

            resource.validateResponse(success, error)

            assertThat(resource.error).isNull()
            assertThat(error.value).isNull()
        }
    }
}