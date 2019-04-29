package com.keiron.babylonhealth.domain.posts.creator

import com.keiron.babylonhealth.domain.posts.model.Comment
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

class CommentDetailsCreatorTest {

    private lateinit var classUnderTest: CommentDetailsCreator
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        classUnderTest = CommentDetailsCreator()
    }

    @Test
    fun `given comment and avatar url when create then CommentDetails returned`() {
        // Given
        val expectedId = 123
        val expectedPostId = 456
        val expectedName = "name"
        val expectedEmail = "email"
        val expectedBody = "body"
        val expectedComment = mock<Comment> {
            on { postId } doReturn expectedPostId
            on { id } doReturn expectedId
            on { name } doReturn expectedName
            on { body } doReturn expectedBody
            on { email } doReturn expectedEmail
        }

        val expectedAvatarUrl = "url"
        val params = mock<CommentDetailsCreator.Params> {
            on { comment } doReturn expectedComment
            on { avatarUrl } doReturn expectedAvatarUrl
        }

        // When
        val commentDetails = classUnderTest.create(params)

        // Then

        with(commentDetails) {
            assertEquals(expectedPostId, postId)
            assertEquals(expectedId, id)
            assertEquals(expectedName, name)
            assertEquals(expectedEmail, email)
            assertEquals(expectedBody, body)
            assertEquals(expectedAvatarUrl, avatarUrl)
        }
    }
}