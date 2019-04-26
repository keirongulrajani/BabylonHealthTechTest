package com.keiron.babylonhealth.domain.posts.usecase

import com.keiron.babylonhealth.domain.posts.exception.NoPostFoundException
import com.keiron.babylonhealth.domain.posts.model.PostDetails
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetPostByIdUseCaseTest {

    @Mock
    private lateinit var getAllPostsWithDetailsUseCase: GetAllPostsWithDetailsUseCase

    private lateinit var classUnderTest: GetPostByIdUseCase
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        classUnderTest = GetPostByIdUseCase(getAllPostsWithDetailsUseCase)
    }

    @Test
    fun `given post list from getAllPostsWithDetailsUseCase contains post when build use case then correct post returned`() {
        // Given
        val expectedId = 123
        val expectedPost = mock<PostDetails> {
            on { id } doReturn expectedId
        }
        val otherPost = mock<PostDetails> {
            on { id } doReturn 456
        }

        whenever(getAllPostsWithDetailsUseCase.buildUseCase()).thenReturn(Single.just(listOf(expectedPost, otherPost)))

        // When
        val testObserver = classUnderTest.buildUseCase(expectedId).test()

        // Then
        verify(getAllPostsWithDetailsUseCase).buildUseCase()

        testObserver.assertComplete()
            .assertNoErrors()
            .assertValue(expectedPost)
    }

    @Test
    fun `given post list from getAllPostsWithDetailsUseCase does not contain post when build use case then throws NoPostFoundException`() {
        // Given
        val post = mock<PostDetails> {
            on { id } doReturn 123
        }
        val otherPost = mock<PostDetails> {
            on { id } doReturn 456
        }

        whenever(getAllPostsWithDetailsUseCase.buildUseCase()).thenReturn(Single.just(listOf(post, otherPost)))

        // When
        val testObserver = classUnderTest.buildUseCase(789).test()

        // Then
        verify(getAllPostsWithDetailsUseCase).buildUseCase()

        testObserver.assertError(NoPostFoundException::class.java)
            .assertNoValues()
            .assertNotComplete()
    }

    @Test
    fun `given getAllPostsWithDetailsUseCase throws error when build use case then throws same error`() {
        // Given
        val throwable = Throwable()
        whenever(getAllPostsWithDetailsUseCase.buildUseCase()).thenReturn(Single.error(throwable))

        // When
        val testObserver = classUnderTest.buildUseCase(789).test()

        // Then
        verify(getAllPostsWithDetailsUseCase).buildUseCase()

        testObserver.assertError(throwable)
            .assertNoValues()
            .assertNotComplete()
    }
}