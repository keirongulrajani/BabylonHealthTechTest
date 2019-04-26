package com.keiron.techtest.section.details.mapper

import com.keiron.babylonhealth.domain.posts.model.Comment
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import org.junit.Test

class CommentToCommentUiModelMapperTest {

    private lateinit var classUnderTest: CommentToCommentUiModelMapper
    @Before
    fun setUp() {
        classUnderTest = CommentToCommentUiModelMapper()
    }

    @Test
    fun `given Comment when mapToPresentation then returns mapped CommentUiModel`() {
        // Given
        val expectedName = "name"
        val expectedEmail = "email"
        val expectedBody = "body"
        val comment = mock<Comment> {
            on { name } doReturn expectedName
            on { email } doReturn expectedEmail
            on { body } doReturn expectedBody
        }

        // When
        val uiModel = classUnderTest.mapToPresentation(comment)

        // Then
        with(uiModel) {
            kotlin.test.assertEquals(expectedName, name)
            kotlin.test.assertEquals(expectedEmail, email)
            kotlin.test.assertEquals(expectedBody, body)
        }
    }
}