package com.keiron.techtest.section.details.mapper

import com.keiron.babylonhealth.domain.common.model.CommentDetails
import com.keiron.babylonhealth.ui.components.mapper.ImageUrlToImageUiModelMapper
import com.keiron.babylonhealth.ui.components.model.ImageUiModel
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import kotlin.test.assertEquals

class CommentToCommentUiModelMapperTest {

    @Mock
    private lateinit var imageUrlToImageUiModelMapper: ImageUrlToImageUiModelMapper

    private lateinit var classUnderTest: CommentToCommentUiModelMapper
    @Before
    fun setUp() {
        initMocks(this)
        classUnderTest = CommentToCommentUiModelMapper(imageUrlToImageUiModelMapper)
    }

    @Test
    fun `given Comment when mapToPresentation then returns mapped CommentUiModel`() {
        // Given
        val expectedName = "name"
        val expectedEmail = "email"
        val expectedBody = "body"
        val expectedAvatarUrl = "url"
        val comment = mock<CommentDetails> {
            on { name } doReturn expectedName
            on { email } doReturn expectedEmail
            on { body } doReturn expectedBody
            on { avatarUrl } doReturn expectedAvatarUrl
        }
        val expectedAvatarImageUiModel = mock<ImageUiModel>()
        whenever(imageUrlToImageUiModelMapper.mapToPresentation(expectedAvatarUrl)).thenReturn(expectedAvatarImageUiModel)

        // When
        val uiModel = classUnderTest.mapToPresentation(comment)

        // Then
        with(uiModel) {
            assertEquals(expectedName, name)
            assertEquals(expectedEmail, email)
            assertEquals(expectedBody, body)
            assertEquals(expectedAvatarImageUiModel, avatar)
        }
    }
}