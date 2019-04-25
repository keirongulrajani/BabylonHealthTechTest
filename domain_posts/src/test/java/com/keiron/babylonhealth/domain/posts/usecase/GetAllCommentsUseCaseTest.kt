package com.keiron.babylonhealth.domain.posts.usecase

import com.keiron.babylonhealth.domain.posts.model.Comment
import com.keiron.babylonhealth.domain.posts.repository.PostRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetAllCommentsUseCaseTest {
    @Mock
    private lateinit var postRepository: PostRepository

    private lateinit var classUnderTest: GetAllCommentsUseCase
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        classUnderTest = GetAllCommentsUseCase(postRepository)
    }

    @Test
    fun `given comment list when build use case then comments fetched from repository and returned`() {
        // Given
        val expectedCommentList = listOf(mock<Comment>())
        whenever(postRepository.getAllComments()).thenReturn(Single.just(expectedCommentList))

        // When
        val testObserver = classUnderTest.buildUseCase().test()

        // Then
        verify(postRepository).getAllComments()

        testObserver.assertComplete()
            .assertNoErrors()
            .assertValue(expectedCommentList)
    }
}