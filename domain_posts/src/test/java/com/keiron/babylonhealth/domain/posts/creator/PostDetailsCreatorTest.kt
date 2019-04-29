package com.keiron.babylonhealth.domain.posts.creator

import com.keiron.babylonhealth.domain.accounts.model.User
import com.keiron.babylonhealth.domain.common.model.CommentDetails
import com.keiron.babylonhealth.domain.common.model.UserDetails
import com.keiron.babylonhealth.domain.posts.model.Comment
import com.keiron.babylonhealth.domain.posts.model.Post
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

class PostDetailsCreatorTest {
    @Mock
    private lateinit var userDetailsCreator: UserDetailsCreator
    @Mock
    private lateinit var commentDetailsCreator: CommentDetailsCreator

    private lateinit var classUnderTest: PostDetailsCreator
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        classUnderTest = PostDetailsCreator(userDetailsCreator, commentDetailsCreator)
    }

    @Test
    fun `given comment and avatar url when create then CommentDetails returned`() {
        // Given
        val expectedId = 123
        val expectedTitle = "title"
        val expectedBody = "body"
        val expectedPost = mock<Post> {
            on { id } doReturn expectedId
            on { title } doReturn expectedTitle
            on { body } doReturn expectedBody
        }

        val user = mock<User>()
        val userAvatar = "userAvatar"
        val userPair = Pair(user, userAvatar)
        val comment = mock<Comment>()
        val commentAvatar = "commentAvatar"
        val commentPair = Pair(comment, commentAvatar)
        val params = mock<PostDetailsCreator.Params> {
            on { post } doReturn expectedPost
            on { authorWithAvatar } doReturn userPair
            on { commentsWithAvatars } doReturn listOf(commentPair)
        }

        val expectedAuthor = mock<UserDetails>()
        whenever(userDetailsCreator.create(UserDetailsCreator.Params(user, userAvatar))).thenReturn(expectedAuthor)
        val expectedComments = mock<CommentDetails>()
        whenever(commentDetailsCreator.create(CommentDetailsCreator.Params(comment, commentAvatar))).thenReturn(expectedComments)

        // When
        val postDetails = classUnderTest.create(params)

        // Then

        with(postDetails) {
            assertEquals(expectedId, id)
            assertEquals(expectedTitle, title)
            assertEquals(expectedBody, body)
            assertEquals(expectedAuthor, author)
            assertEquals(listOf(expectedComments), comments)
        }
    }
}