package com.keiron.data.posts.datasource

import com.keiron.data.posts.client.PostClient
import com.keiron.data.posts.model.CommentDto
import com.keiron.data.posts.model.PostDto
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

class PostDataSourceTest {
    @Mock
    private lateinit var postClient: PostClient

    private lateinit var classUnderTest: PostDataSource

    @Before
    fun setUp() {
        initMocks(this)

        whenever(postClient.getAllComments()).thenReturn(Single.just(emptyList()))
        whenever(postClient.getAllPosts()).thenReturn(Single.just(emptyList()))

        classUnderTest = PostDataSource(postClient)
    }

    @Test
    fun `when getAllComments then comments fetched from client`() {
        // When
        classUnderTest.getAllComments()

        // Then
        verify(postClient).getAllComments()
    }

    @Test
    fun `when getAllPosts then posts fetched from client`() {
        // When
        classUnderTest.getAllPosts()

        // Then
        verify(postClient).getAllPosts()
    }

    @Test
    fun `given list of comments from client when getAllComments then comments from client returned`() {
        // Given
        val commentDto = mock<CommentDto>()
        val expectedData = listOf(commentDto)
        whenever(postClient.getAllComments()).thenReturn(Single.just(expectedData))

        // When
        val testObserver = classUnderTest.getAllComments().test()

        // Then
        testObserver.assertNoErrors()
            .assertComplete()
            .assertValue(expectedData)
    }

    @Test
    fun `given list of posts from client when getAllComments then comments from client returned`() {
        // Given
        val postDto = mock<PostDto>()
        val expectedData = listOf(postDto)
        whenever(postClient.getAllPosts()).thenReturn(Single.just(expectedData))

        // When
        val testObserver = classUnderTest.getAllPosts().test()

        // Then
        testObserver.assertNoErrors()
            .assertComplete()
            .assertValue(expectedData)
    }
}