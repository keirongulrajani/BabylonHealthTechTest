package com.keiron.data.posts.mapper

import com.keiron.data.posts.model.PostDto
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class PostDtoToPostMapperTest {
    private lateinit var classUnderTest: PostDtoToPostMapper

    @Before
    fun setUp() {
        classUnderTest = PostDtoToPostMapper()
    }

    @Test
    fun `given post dto when mapToDomain then maps to Post`() {
        // Given
        val expectedUserId = 123
        val expectedId = 456
        val expectedTitle = "title"
        val expectedBody = "body"
        val postDto = mock<PostDto> {
            on { userId } doReturn expectedUserId
            on { id } doReturn expectedId
            on { title } doReturn expectedTitle
            on { body } doReturn expectedBody
        }

        // When
        val post = classUnderTest.mapToDomain(postDto)

        // Then
        with(post) {
            assertEquals(expectedUserId, userId)
            assertEquals(expectedId, id)
            assertEquals(expectedTitle, title)
            assertEquals(expectedBody, body)
        }
    }
}