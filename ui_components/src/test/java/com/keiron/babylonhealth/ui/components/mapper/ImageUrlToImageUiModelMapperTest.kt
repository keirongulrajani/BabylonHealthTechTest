package com.keiron.babylonhealth.ui.components.mapper

import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

class ImageUrlToImageUiModelMapperTest {

    private lateinit var classUnderTest: ImageUrlToImageUiModelMapper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        classUnderTest = ImageUrlToImageUiModelMapper()
    }

    @Test
    fun `given Comment when mapToPresentation then returns mapped CommentUiModel`() {
        // Given
        val expectedUrl = "url"

        // When
        val uiModel = classUnderTest.mapToPresentation(expectedUrl)

        // Then
        with(uiModel) {
            assertEquals(expectedUrl, imageUrl)
        }
    }
}