package com.keiron.babylonhealth.domain.posts.usecase

import com.keiron.babylonhealth.domain.accounts.model.User
import com.keiron.babylonhealth.domain.accounts.usecase.GetAllUsersUseCase
import com.keiron.babylonhealth.domain.accounts.usecase.GetAvatarUrlForEmailUseCase
import com.keiron.babylonhealth.domain.common.model.PostDetails
import com.keiron.babylonhealth.domain.posts.creator.PostDetailsCreator
import com.keiron.babylonhealth.domain.posts.model.Comment
import com.keiron.babylonhealth.domain.posts.model.Post
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetAllPostsWithDetailsUseCaseTest {

    @Mock
    private lateinit var getAllPostsUseCase: GetAllPostsUseCase
    @Mock
    private lateinit var getAllCommentsUseCase: GetAllCommentsUseCase
    @Mock
    private lateinit var getAllUsersUseCase: GetAllUsersUseCase
    @Mock
    private lateinit var getAvatarUrlForEmailUseCase: GetAvatarUrlForEmailUseCase
    @Mock
    private lateinit var postDetailsCreator: PostDetailsCreator

    private lateinit var classUnderTest: GetAllPostsWithDetailsUseCase
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        classUnderTest = GetAllPostsWithDetailsUseCase(
            getAllPostsUseCase,
            getAllCommentsUseCase,
            getAllUsersUseCase,
            getAvatarUrlForEmailUseCase,
            postDetailsCreator
        )
    }

    @Test
    fun `given post list and user list and comments list when build use case then post returned with matched author and comments with avatars`() {
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

        val userEmail = "userEmail"
        val expectedUser = mock<User> {
            on { id } doReturn expectedUserId
            on { email } doReturn userEmail
        }

        whenever(getAllUsersUseCase.buildUseCase()).thenReturn(Single.just(listOf(expectedUser)))

        val commentEmail = "commentEmail"
        val expectedComment = mock<Comment> {
            on { postId } doReturn expectedId
            on { email } doReturn commentEmail
        }

        whenever(getAllCommentsUseCase.buildUseCase()).thenReturn(Single.just(listOf(expectedComment)))

        val userAvatarUrl = "userAvatarUrl"
        whenever(getAvatarUrlForEmailUseCase.buildUseCase(userEmail)).thenReturn(Single.just(userAvatarUrl))
        val commentAvatarUrl = "commentAvatarUrl"
        whenever(getAvatarUrlForEmailUseCase.buildUseCase(commentEmail)).thenReturn(Single.just(commentAvatarUrl))
        val expectedPostDetails = mock<PostDetails>()
        whenever(postDetailsCreator.create(PostDetailsCreator.Params(Pair(expectedUser, userAvatarUrl), listOf(Pair(expectedComment, commentAvatarUrl)), expectedPost))).thenReturn(expectedPostDetails)
        // When
        val testObserver = classUnderTest.buildUseCase().test()

        // Then
        verify(getAllPostsUseCase).buildUseCase()
        verify(getAllCommentsUseCase).buildUseCase()
        verify(getAllUsersUseCase).buildUseCase()
        verify(getAvatarUrlForEmailUseCase).buildUseCase(userEmail)
        verify(getAvatarUrlForEmailUseCase).buildUseCase(commentEmail)

        testObserver.assertComplete()
            .assertNoErrors()
            .assertValue(listOf(expectedPostDetails))
    }

    @Test
    fun `given post list and user list and comments list and post list when build use case then post returned with matched author and comments`() {
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

        val userEmail = "userEmail"
        val expectedUser = mock<User> {
            on { id } doReturn expectedUserId
            on { email } doReturn userEmail
        }

        val otherUser = mock<User> {
            on { id } doReturn 4
            on { email } doReturn userEmail
        }

        whenever(getAllUsersUseCase.buildUseCase()).thenReturn(Single.just(listOf(expectedUser, otherUser)))

        val expectedComment = mock<Comment> {
            on { postId } doReturn expectedId
            on { email } doReturn userEmail
        }
        val otherComment = mock<Comment> {
            on { postId } doReturn 111
            on { email } doReturn userEmail
        }
        whenever(getAllCommentsUseCase.buildUseCase()).thenReturn(Single.just(listOf(expectedComment, otherComment)))

        val avatarUrl = "avatarUrl"
        whenever(getAvatarUrlForEmailUseCase.buildUseCase(any())).thenReturn(Single.just(avatarUrl))
        val expectedPostDetails = mock<PostDetails>()
        whenever(postDetailsCreator.create(PostDetailsCreator.Params(Pair(expectedUser, avatarUrl), listOf(Pair(expectedComment, avatarUrl)), expectedPost))).thenReturn(expectedPostDetails)
        // When
        val testObserver = classUnderTest.buildUseCase().test()

        // Then
        verify(getAllPostsUseCase).buildUseCase()
        verify(getAllCommentsUseCase).buildUseCase()
        verify(getAllUsersUseCase).buildUseCase()

        testObserver.assertComplete()
            .assertNoErrors()
            .assertValue(listOf(expectedPostDetails))
    }
}