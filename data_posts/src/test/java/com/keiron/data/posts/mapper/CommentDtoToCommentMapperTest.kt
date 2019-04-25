package com.keiron.data.posts.mapper

import com.keiron.data.posts.model.CommentDto
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class CommentDtoToCommentMapperTest {
    private lateinit var classUnderTest: CommentDtoToCommentMapper

    @Before
    fun setUp() {
        classUnderTest = CommentDtoToCommentMapper()
    }

    @Test
    fun `given comment dto when mapToDomain then maps to Comment`() {
        // Given
        val expectedPostId = 123
        val expectedId = 456
        val expectedName = "name"
        val expectedBody = "body"
        val expectedEmail = "email"
        val postDto = mock<CommentDto> {
            on { postId } doReturn expectedPostId
            on { id } doReturn expectedId
            on { name } doReturn expectedName
            on { email } doReturn expectedEmail
            on { body } doReturn expectedBody
        }

        // When
        val comment = classUnderTest.mapToDomain(postDto)

        // Then
        with(comment) {
            assertEquals(expectedPostId, postId)
            assertEquals(expectedId, id)
            assertEquals(expectedName, name)
            assertEquals(expectedEmail, email)
            assertEquals(expectedBody, body)
        }
    }
}