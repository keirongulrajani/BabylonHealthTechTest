package com.keiron.babylonhealth.domain.posts.usecase

import com.keiron.babylonhealth.domain.posts.model.Comment
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetCommentsForPostIdUseCaseTest {

    @Mock
    private lateinit var getAllCommentsUseCase: GetAllCommentsUseCase

    private lateinit var classUnderTest: GetCommentsForPostIdUseCase
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        classUnderTest = GetCommentsForPostIdUseCase(getAllCommentsUseCase)
    }

    @Test
    fun `given comments list from GetAllCommentsUseCase contains post id when build use case then correct comments returned`() {
        // Given
        val expectedId = 123
        val expectedComment = mock<Comment> {
            on { postId } doReturn expectedId
        }
        val otherComment = mock<Comment> {
            on { postId } doReturn 456
        }

        whenever(getAllCommentsUseCase.buildUseCase()).thenReturn(Single.just(listOf(expectedComment, otherComment)))

        // When
        val testObserver = classUnderTest.buildUseCase(expectedId).test()

        // Then
        verify(getAllCommentsUseCase).buildUseCase()

        testObserver.assertComplete()
            .assertNoErrors()
            .assertValue(listOf(expectedComment))
    }

    @Test
    fun `given comments list from GetAllCommentsUseCase does not contains post id when build use case then empty list returned`() {
        // Given
        val comment = mock<Comment> {
            on { postId } doReturn 123
        }
        val otherComment = mock<Comment> {
            on { postId } doReturn 456
        }

        whenever(getAllCommentsUseCase.buildUseCase()).thenReturn(Single.just(listOf(comment, otherComment)))

        // When
        val testObserver = classUnderTest.buildUseCase(789).test()

        // Then
        verify(getAllCommentsUseCase).buildUseCase()

        testObserver.assertComplete()
            .assertNoErrors()
            .assertValue(emptyList())
    }
}