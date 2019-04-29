package com.keiron.babylonhealth.domain.accounts.usecase

import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class GetAvatarUrlForEmailUseCaseTest {

    private lateinit var classUnderTest: GetAvatarUrlForEmailUseCase
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        classUnderTest = GetAvatarUrlForEmailUseCase()
    }

    @Test
    fun `given email when build use case then avatar url constructed and returned`() {
        // Given
        val email = "email"

        // When
        val testObserver = classUnderTest.buildUseCase(email).test()

        // Then

        testObserver.assertComplete()
            .assertNoErrors()
            .assertValue("https://api.adorable.io/avatars/285/email.png")
    }
}