package com.keiron.babylonhealth.domain.posts.usecase

import com.keiron.babylonhealth.domain.posts.model.Post
import com.keiron.babylonhealth.domain.posts.repository.PostRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetAllPostsUseCaseTest {

    @Mock
    private lateinit var postRepository: PostRepository

    private lateinit var classUnderTest: GetAllPostsUseCase
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        classUnderTest = GetAllPostsUseCase(postRepository)
    }

    @Test
    fun `given post list when build use case then posts fetched from repository and returned`() {
        // Given
        val expectedPostList = listOf(mock<Post>())
        whenever(postRepository.getAllPosts()).thenReturn(Single.just(expectedPostList))

        // When
        val testObserver = classUnderTest.buildUseCase().test()

        // Then
        verify(postRepository).getAllPosts()

        testObserver.assertComplete()
            .assertNoErrors()
            .assertValue(expectedPostList)
    }
}