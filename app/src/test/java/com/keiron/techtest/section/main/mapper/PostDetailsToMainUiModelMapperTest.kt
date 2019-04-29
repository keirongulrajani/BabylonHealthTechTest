package com.keiron.techtest.section.main.mapper

import com.keiron.babylonhealth.domain.common.model.CommentDetails
import com.keiron.babylonhealth.domain.common.model.PostDetails
import com.keiron.babylonhealth.domain.common.model.UserDetails
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

class PostDetailsToMainUiModelMapperTest {
    @Mock
    private lateinit var imageUrlToImageUiModelMapper: ImageUrlToImageUiModelMapper

    private lateinit var classUnderTest: PostDetailsToMainUiModelMapper
    @Before
    fun setUp() {
        initMocks(this)
        classUnderTest = PostDetailsToMainUiModelMapper(imageUrlToImageUiModelMapper)
    }

    @Test
    fun `given PostDetails when mapToPresentation then returns mapped MainUiModel`() {
        // Given
        val expectedPostTitle = "title"
        val expectedAuthorTitle = "username"
        val expectedAvatarUrl = "url"
        val user = mock<UserDetails> {
            on { username } doReturn expectedAuthorTitle
            on { avatarUrl } doReturn expectedAvatarUrl
        }
        val expectedCommentSize = 10
        val expectedId = 123
        val postDetails = mock<PostDetails> {
            on { id } doReturn expectedId
            on { title } doReturn expectedPostTitle
            on { author } doReturn user
            on { comments } doReturn MutableList<CommentDetails>(expectedCommentSize) { index -> mock() }
        }
        val expectedAvatarImageUiModel = mock<ImageUiModel>()
        whenever(imageUrlToImageUiModelMapper.mapToPresentation(expectedAvatarUrl)).thenReturn(expectedAvatarImageUiModel)

        // When
        val uiModel = classUnderTest.mapToPresentation(postDetails)

        // Then
        with(uiModel) {
            assertEquals(expectedId, id)
            assertEquals(expectedPostTitle, postTitle)
            assertEquals(expectedAuthorTitle, authorTitle)
            assertEquals(expectedAvatarImageUiModel, authorAvatar)
            assertEquals(expectedCommentSize, numberOfComments)
        }
    }
}