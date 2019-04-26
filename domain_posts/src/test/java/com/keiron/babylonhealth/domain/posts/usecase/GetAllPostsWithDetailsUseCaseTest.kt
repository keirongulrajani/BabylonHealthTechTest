package com.keiron.babylonhealth.domain.posts.usecase

import com.keiron.babylonhealth.domain.accounts.model.User
import com.keiron.babylonhealth.domain.accounts.usecase.GetAllUsersUseCase
import com.keiron.babylonhealth.domain.posts.model.Comment
import com.keiron.babylonhealth.domain.posts.model.Post
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

class GetAllPostsWithDetailsUseCaseTest {

    @Mock
    private lateinit var getAllPostsUseCase: GetAllPostsUseCase
    @Mock
    private lateinit var getAllCommentsUseCase: GetAllCommentsUseCase
    @Mock
    private lateinit var getAllUsersUseCase: GetAllUsersUseCase

    private lateinit var classUnderTest: GetAllPostsWithDetailsUseCase
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        classUnderTest = GetAllPostsWithDetailsUseCase(
            getAllPostsUseCase,
            getAllCommentsUseCase,
            getAllUsersUseCase
        )
    }

    @Test
    fun `given post list from getAllPostsUseCase contains post when build use case then post returned with matched author and comments`() {
        // Given
        val expectedId = 123
        val expectedUserId = 12
        val expectedTitle = "expectedTitle"
        val expectedBody = "body"
        val expectedPost = mock<Post> {
            on { id } doReturn expectedId
            on { userId } doReturn expectedUserId
            on { title } doReturn expectedTitle
            on { body } doReturn expectedBody
        }

        whenever(getAllPostsUseCase.buildUseCase()).thenReturn(Single.just(listOf(expectedPost)))

        val expectedUser = mock<User> {
            on { id } doReturn expectedUserId
        }

        val otherUser = mock<User> {
            on { id } doReturn 4
        }

        whenever(getAllUsersUseCase.buildUseCase()).thenReturn(Single.just(listOf(expectedUser, otherUser)))

        val expectedComment = mock<Comment> {
            on { postId } doReturn expectedId
        }
        val otherComment = mock<Comment> {
            on { postId } doReturn 111
        }
        whenever(getAllCommentsUseCase.buildUseCase()).thenReturn(Single.just(listOf(expectedComment, otherComment)))
        // When
        val testObserver = classUnderTest.buildUseCase().test()

        // Then
        verify(getAllPostsUseCase).buildUseCase()
        verify(getAllCommentsUseCase).buildUseCase()
        verify(getAllUsersUseCase).buildUseCase()

        val values = testObserver.assertComplete()
            .assertNoErrors()
            .values()

        assertEquals(1, values.size)
        val returnedList = values.first()
        assertEquals(1, returnedList.size)
        with(returnedList.first()) {
            assertEquals(expectedId, id)
            assertEquals(expectedTitle, title)
            assertEquals(expectedBody, body)
            assertEquals(expectedUser, author)
            assertEquals(listOf(expectedComment), comments)
        }
    }
}