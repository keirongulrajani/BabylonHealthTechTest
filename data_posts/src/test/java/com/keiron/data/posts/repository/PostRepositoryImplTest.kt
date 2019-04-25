package com.keiron.data.posts.repository

import com.keiron.babylonhealth.domain.posts.model.Comment
import com.keiron.babylonhealth.domain.posts.model.Post
import com.keiron.data.posts.datasource.PostDataSource
import com.keiron.data.posts.mapper.CommentDtoToCommentMapper
import com.keiron.data.posts.mapper.PostDtoToPostMapper
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

class PostRepositoryImplTest {
    @Mock
    private lateinit var postDataSource: PostDataSource
    @Mock
    private lateinit var commentDtoToCommentMapper: CommentDtoToCommentMapper
    @Mock
    private lateinit var postDtoToPostMapper: PostDtoToPostMapper
    private lateinit var classUnderTest: PostRepositoryImpl

    @Before
    fun setUp() {
        initMocks(this)
        classUnderTest = PostRepositoryImpl(
            postDataSource,
            commentDtoToCommentMapper,
            postDtoToPostMapper
        )
    }

    @Test
    fun `given list of posts when getAllPosts then returns mapped post list`() {
        // Given
        val postList = listOf(mock<PostDto>())
        val mappedPostList = listOf(mock<Post>())
        whenever(postDataSource.getAllPosts()).thenReturn(Single.just(postList))
        whenever(postDtoToPostMapper.mapToDomain(postList)).thenReturn(mappedPostList)

        // When
        val testObserver = classUnderTest.getAllPosts().test()

        // Then
        verify(postDataSource).getAllPosts()
        verify(postDtoToPostMapper).mapToDomain(postList)
        testObserver.assertComplete()
            .assertNoErrors()
            .assertValue(mappedPostList)
    }

    @Test
    fun `given list of comments when getAllComments then returns mapped comment list`() {
        // Given
        val commentList = listOf(mock<CommentDto>())
        val mappedCommentList = listOf(mock<Comment>())
        whenever(postDataSource.getAllComments()).thenReturn(Single.just(commentList))
        whenever(commentDtoToCommentMapper.mapToDomain(commentList)).thenReturn(mappedCommentList)

        // When
        val testObserver = classUnderTest.getAllComments().test()

        // Then
        verify(postDataSource).getAllComments()
        verify(commentDtoToCommentMapper).mapToDomain(commentList)
        testObserver.assertComplete()
            .assertNoErrors()
            .assertValue(mappedCommentList)
    }
}