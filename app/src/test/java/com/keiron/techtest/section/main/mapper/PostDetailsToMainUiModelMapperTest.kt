package com.keiron.techtest.section.main.mapper

import com.keiron.babylonhealth.domain.accounts.model.User
import com.keiron.babylonhealth.domain.posts.model.Comment
import com.keiron.babylonhealth.domain.posts.model.PostDetails
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class PostDetailsToMainUiModelMapperTest {
    private lateinit var classUnderTest: PostDetailsToMainUiModelMapper
    @Before
    fun setUp() {
        classUnderTest = PostDetailsToMainUiModelMapper()
    }

    @Test
    fun `given PostDetails when mapToPresentation then returns mapped MainUiModel`() {
        // Given
        val expectedPostTitle = "title"
        val expectedAuthorTitle = "username"
        val user = mock<User> {
            on { username } doReturn expectedAuthorTitle
        }
        val expectedCommentSize = 10
        val expectedId = 123
        val postDetails = mock<PostDetails> {
            on { id } doReturn expectedId
            on { title } doReturn expectedPostTitle
            on { author } doReturn user
            on { comments } doReturn MutableList<Comment>(expectedCommentSize) { index -> mock() }
        }

        // When
        val uiModel = classUnderTest.mapToPresentation(postDetails)

        // Then
        with(uiModel) {
            assertEquals(expectedId, id)
            assertEquals(expectedPostTitle, postTitle)
            assertEquals(expectedAuthorTitle, authorTitle)
            assertEquals(expectedCommentSize, numberOfComments)
        }
    }
}